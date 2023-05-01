package com.onlineCourse.service.interfaces;

import com.onlineCourse.entities.Course;
import com.onlineCourse.entities.User;

import java.util.List;

public interface CourseService {
    List<Course> getCourseList();
    List<Course> search(String searchText);

    List<Course> findByIdIn(List<Integer> courseIdList);


    List<Course> getEnrolledCourseList(Integer userId);
}
