package com.onlineCourse.service.impl;

import com.onlineCourse.entities.Course;
import com.onlineCourse.repository.CourseRepository;
import com.onlineCourse.service.interfaces.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getCourseList() {
        return courseRepository.findAll();
    }
}