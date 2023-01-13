package com.onlinecourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = { "com.onlinecourse.dao"})
@ComponentScan(basePackages = { "com.onlinecourse.*" })
@EntityScan(basePackages = { "com.onlinecourse.entities" })   
@Slf4j
public class OnlinecourseApplication {

	public static void main(String[] args) {
		try {
			log.info("Application Starting...");
			SpringApplication.run(OnlinecourseApplication.class, args);
			log.info("Application Started Successfully.");
		//} catch (SilentExitException ex) {
		} catch (Exception ex) {
			log.error("Failed to start application" + ex);
		}
	}

}
