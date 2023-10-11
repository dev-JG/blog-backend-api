package com.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogBackendApiApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application, application-domain");
        SpringApplication.run(BlogBackendApiApplication.class, args);
    }
}