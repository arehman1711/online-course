package com.onlineCourse.service.interfaces;

import com.onlineCourse.entities.User;

public interface UserService {

    boolean isValidUser(User user);
    boolean isUserAlreadyExists(String email);

    User getUserByEmail(String email);
}
