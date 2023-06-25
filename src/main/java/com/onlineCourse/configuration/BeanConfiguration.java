package com.onlineCourse.configuration;


import com.onlineCourse.service.impl.EmailServiceImpl;
import com.onlineCourse.service.interfaces.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Slf4j
@Configuration
public class BeanConfiguration {
    @Autowired
    private JavaMailSender javaMailSender;

    @Bean
    public EmailService emailService(){
        log.info("Creating EmailService Bean..! JavaMailSender={}", javaMailSender);
        return new EmailServiceImpl(javaMailSender);
    }
}
