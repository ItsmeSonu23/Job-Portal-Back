package com.jobportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main application class for the Job Portal application.
 * Configures and launches the Spring Boot application.
 */
@SpringBootApplication
@EnableScheduling
public class JobPortalApplication {

    /**
     * Main method that starts the Spring Boot application.
     * Bootstraps the application context and runs the embedded server.
     *
     * @param args Command line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(JobPortalApplication.class, args);
    }

}
