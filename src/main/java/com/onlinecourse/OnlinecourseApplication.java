package com.onlinecourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class OnlinecourseApplication {

	public static void main(String[] args) {
		try {
			log.info("Application Starting...");
			SpringApplication.run(OnlinecourseApplication.class, args);
			log.info("Application Started Successfully.");
		} catch (Exception ex) {
			log.error("Failed to start application" + ex.getMessage());
		}
	}

}
