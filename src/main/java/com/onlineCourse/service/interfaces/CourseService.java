package com.onlineCourse.service.interfaces;

import com.onlineCourse.entities.Course;

import java.util.List;

public interface CourseService {
    List<Course> getCourseList();
    List<Course> search(String searchText);

}
