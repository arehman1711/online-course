package com.onlineCourse.service.impl;

import com.onlineCourse.entities.Course;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class CourseServiceImplTest {

    @Autowired
    CourseServiceImpl courseService;

    @Before
    public void setup() throws Exception {

    }

    @Test
    public void testGetCourseList_ReturnResult() throws Exception {
        List<Course> courseList = courseService.getCourseList(1);
        log.info("courseList = " + courseList);
        Assert.assertNotNull(courseList);
    }

}