package com.onlineCourse.repository.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlineCourse.entities.User;
import com.onlineCourse.repository.UserRepository;
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
public class FileUserRepository {
    @Autowired
    private UserRepository userRepository;
    @Value("${file.repo.path:/}")
    private String fileRepoPath;

    @Value("${file.repo.enable:false}")
    private Boolean isFileRepoEnabled;

    @Autowired
    ObjectMapper mapper;

    public User save(User user) {
        return save(user, getUserPath(user.getId()));
    }

    private String getUserPath(Integer id) {
        String fileName = "user-".concat(String.valueOf(id)).concat(".json");
        return getUserDirectoryPath() + fileName;
    }

    private User save(User user, String path) {
        if (isFileRepoEnabled) {
            log.info("Insert user to file-repo : User=" + user + ", Path=" + path);
            try {
                File file = new File(path); // create file
                mapper.writeValue(file, user); // Convert object to json and Write to the file.
                user = getById(user.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return user ;
    }

    public User getById(Integer id) throws IOException {
        File fileData = new File(getUserPath(id)); //Get file data from Path
        User user = mapper.readValue(fileData, User.class); //Map file data to Java pojo
        log.info("Successfully fetched user Data from File. User={}", user);
        return user;
    }

    public void deleteById(Integer id) {
        if(isFileRepoEnabled) {
            try {
                File file = new File(getUserPath(id));
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
        loadUserTable();
    }

    private void loadUserTable() {
        List<User> userList = userRepository.findAll();
        log.info("Loading user table into file Repository. userList={}", userList);
        safe(userList).forEach(this::save);
    }

    private void createDirectories() {
        try {
            Files.createDirectories(Paths.get(getUserDirectoryPath()));
        } catch (Exception e) {
            log.error("Unable to create Directory");
            e.printStackTrace();
        }
    }

    private String getUserDirectoryPath() {
        return fileRepoPath + "user" + "/";
    }

}
