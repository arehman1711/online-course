package com.onlineCourse.repository;

import com.onlineCourse.entities.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ContactUsRepository extends JpaRepository<ContactUs, Integer> {
}