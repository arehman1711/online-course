package com.onlineCourse.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlineCourse.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

@Component
@Slf4j
public class FileRepository {
    @Value("${file.repo.path:/}")
    private String fileRepoPath;

    @Value("${file.repo.enable:false}")
    private Boolean isFileRepoEnabled;

    @Autowired
    ObjectMapper mapper;

    public User saveUser(User user) {
        return save(user, getUserPath(user.getId()));
    }

    private String getUserPath(Integer id) {
        String fileName = "user".concat(String.valueOf(id)).concat(".json");
        return fileRepoPath + fileName;
    }

    private User save(User user, String path) {
        if (isFileRepoEnabled) {
            log.info("Insert user to file-repo : User=" + user + ", Path=" + path);
            try {
                File file = new File(path); // create file
                mapper.writeValue(file, user); // Convert object to json and Write to the file.
                getById(user.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return user;
    }

    private void getById(Integer id) {
        //read from file
        /// print json
        // convert to java object and print
    }

    private void deleteById(Integer id) {
        //delete File
    }

    @PostConstruct
    public void createDirectories() {
        //file-repo directory create

        //loadDataFromDbToFileRepository();
    }
}
