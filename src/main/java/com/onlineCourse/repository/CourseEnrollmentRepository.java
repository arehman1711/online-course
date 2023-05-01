package com.onlineCourse.repository;

import com.onlineCourse.entities.CourseEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource(exported = false)
@Repository
public interface CourseEnrollmentRepository  extends JpaRepository<CourseEnrollment, Integer> {
    public List<CourseEnrollment> getCourseEnrollmentByUserId(Integer userId);

    public List<CourseEnrollment> getCourseEnrollmentByCourseId(Integer courseId);
}
