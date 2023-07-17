package com.onlineCourse.service.impl;

import com.onlineCourse.entities.Course;
import com.onlineCourse.entities.CourseEnrollment;
import com.onlineCourse.repository.CourseEnrollmentRepository;
import com.onlineCourse.repository.CourseRepository;
import com.onlineCourse.repository.file.FileCourseRepository;
import com.onlineCourse.service.interfaces.CourseService;
import com.onlineCourse.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private FileCourseRepository fileCourseRepository;

    @Autowired
    private CourseEnrollmentRepository courseEnrollmentRepository;

    @Override
    public List<Course> getCourseList(Integer userId) {
        List<Integer> enrolledCourseIds = getEnrolledCourseIds(userId);
        List<Course> courseList =  courseRepository.findAll();
        return stampImageIdAndEnrollmentFlag(courseList,enrolledCourseIds);
    }

    @Override
    public List<Course> search(String searchText, Integer userId) {
        List<Integer> enrolledCourseIds = getEnrolledCourseIds(userId);
        List<Course> courseList =  courseRepository.findByCourseNameContainingIgnoreCase(searchText);
        return stampImageIdAndEnrollmentFlag(courseList, enrolledCourseIds);
    }

    private List<Course> stampImageIdAndEnrollmentFlag(List<Course> courseList, List<Integer> enrolledCourseIds) {
        return Utils.safe(courseList).stream().map(c -> {
            c.setImageId(String.valueOf(c.getId()%10));
            c.setEnrolled(enrolledCourseIds.contains(c.getId()) ? true : false);
            return c;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Course> findByIdIn(List<Integer> courseIdList) {
        return courseRepository.findByIdIn(courseIdList);
    }

    @Override
    public List<Course> getEnrolledCourseList(Integer userId){
        List<Integer> enrolledCourseIds = getEnrolledCourseIds(userId);
        List<Course> courseList = findByIdIn(enrolledCourseIds);
        return stampImageIdAndEnrollmentFlag(courseList, enrolledCourseIds);
    }

    @Override
    public Course save(Course course) {
        course = courseRepository.save(course);
        fileCourseRepository.save(course);
        return course;
    }

    @Override
    public void deleteById(int id) {
        courseRepository.deleteById(id);
        fileCourseRepository.deleteById(id);
    }

    private List<Integer> getEnrolledCourseIds(Integer userId) {
        if (userId != null && userId>0) {
            List<CourseEnrollment> courseEnrollmentList = courseEnrollmentRepository.getCourseEnrollmentByUserId(userId);
            List<Integer> courseIdList = Utils.safe(courseEnrollmentList).stream().map(CourseEnrollment::getCourseId).collect(Collectors.toList());
            return (List<Integer>) Utils.safe(courseIdList);
        } else {
            return Collections.emptyList();
        }
    }
}