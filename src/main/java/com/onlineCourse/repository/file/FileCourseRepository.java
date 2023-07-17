package com.onlineCourse.repository.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlineCourse.entities.Course;
import com.onlineCourse.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.onlineCourse.utils.Utils.safe;

@Component
@Slf4j
public class FileCourseRepository {
    @Autowired
    private CourseRepository courseRepository;
    @Value("${file.repo.path:/}")
    private String fileRepoPath;

    @Value("${file.repo.enable:false}")
    private Boolean isFileRepoEnabled;

    @Autowired
    ObjectMapper mapper;

    public Course save(Course course) {
        return save(course, getCoursePath(course.getId()));
    }

    private String getCoursePath(Integer id) {
        String fileName = "course-".concat(String.valueOf(id)).concat(".json");
        return getCourseDirectoryPath() + fileName;
    }

    private Course save(Course course, String path) {
        if (isFileRepoEnabled) {
            log.info("Insert course to file-repo : course=" + course + ", Path=" + path);
            try {
                File file = new File(path); // create file
                mapper.writeValue(file, course); // Convert object to json and Write to the file.
                course = getById(course.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return course ;
    }

    public Course getById(Integer id) throws IOException {
        File fileData = new File(getCoursePath(id)); //Get file data from Path
        Course course = mapper.readValue(fileData, Course.class); //Map file data to Java pojo
        log.info("Successfully fetched course Data from File. course={}", course);
        return course;
    }

    public void deleteById(Integer id) {
        if(isFileRepoEnabled) {
            try {
                File file = new File(getCoursePath(id));
                if (file.delete()) {
                    log.info("File : {} deleted successfully", file);
                } else {
                    log.error("File : {} could not be deleted", file);
                }
            } catch (Exception e) {
                log.error("Exception while deleting file for id={}", id);
                e.printStackTrace();
            }
        }
    }

    @PostConstruct
    public void loadDataFromDB() {
        createDirectories();
        loadCourseTable();
    }

    private void loadCourseTable() {
        List<Course> courseList = courseRepository.findAll();
        log.info("Loading course table into file Repository. courseList={}", courseList);
        safe(courseList).forEach(this::save);
    }

    private void createDirectories() {
        try {
            Files.createDirectories(Paths.get(getCourseDirectoryPath()));
        } catch (Exception e) {
            log.error("Unable to create Directory");
            e.printStackTrace();
        }
    }

    private String getCourseDirectoryPath() {
        return fileRepoPath + "course" + "/";
    }

}
