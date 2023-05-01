package com.onlineCourse.repository;

import com.onlineCourse.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource(exported = false)
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> findByCourseNameOrCourseDescriptionContainingIgnoreCase(String courseName, String courseDescription);

    List<Course> findByCourseNameContainingIgnoreCase(String searchText);

    List<Course> findByIdIn(List<Integer> courseIdList);
}
