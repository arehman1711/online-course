package com.onlineCourse.service.interfaces;

import com.onlineCourse.entities.Course;

import java.util.List;

public interface CourseService {
    List<Course> getCourseList(Integer userId);
    List<Course> search(String searchText, Integer userId);

    List<Course> findByIdIn(List<Integer> courseIdList);


    List<Course> getEnrolledCourseList(Integer userId);

    Course save(Course course);

    void deleteById(int id);
}
