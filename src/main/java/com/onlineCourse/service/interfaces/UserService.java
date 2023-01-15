package com.onlineCourse.service.interfaces;

import com.onlineCourse.entities.User;

public interface UserService {

    boolean isValidUser(User user);

    User getUserByEmail(String email);
}
