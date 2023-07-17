package com.onlineCourse.service.interfaces;

public interface EmailService {
    void sendEmail(String toEmail, String subject, String body);
}
