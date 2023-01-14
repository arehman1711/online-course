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
        User dbUser = userRepository.getUserByEmail(user.getEmail());
        log.info("DB USER : " +  dbUser);
        if(dbUser!=null && user.getPassword().equals(dbUser.getPassword())){
           return true;
        }
        return false;
    }
}