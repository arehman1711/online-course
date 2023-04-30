package com.onlineCourse.service.impl;

import com.onlineCourse.entities.Course;
import com.onlineCourse.repository.CourseRepository;
import com.onlineCourse.service.interfaces.CourseService;
import com.onlineCourse.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getCourseList() {
        List<Course> courseList =  courseRepository.findAll();
        return stampImageId(courseList);
    }

    @Override
    public List<Course> search(String searchText) {
        List<Course> courseList =  courseRepository.findByCourseNameContainingIgnoreCase(searchText);
        return stampImageId(courseList);
    }

    private List<Course> stampImageId(List<Course> courseList) {
        return Utils.safe(courseList).stream().map(course -> {
            course.setImageId(String.valueOf(course.getId()%10));
            return course;
        }).collect(Collectors.toList());
    }
}