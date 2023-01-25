package com.onlineCourse.service.impl;

import com.onlineCourse.repository.UserRepository;
import com.onlineCourse.entities.User;
import com.onlineCourse.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValidUser(User user) {
        User dbUser = getUserByEmail(user.getEmail());
        log.info("DB USER : " +  dbUser);
        if(dbUser!=null && user.getPassword().equals(dbUser.getPassword())){
            user.setId(dbUser.getId());
            user.setName(dbUser.getName());
            user.setRole(dbUser.getRole());
           return true;
        }
        return false;
    }

    public boolean isUserAlreadyExists(String email) {
        User dbUser = getUserByEmail(email);
        if(dbUser!=null){
            return true;
        }
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public User enroll(User sessionUser) {
        return userRepository.save(sessionUser);
    }
}