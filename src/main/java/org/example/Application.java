package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class Application extends SpringBootServletInitializer{
    public static void main(String[] args) throws Throwable{
        SpringApplication.run(Application.class, args);
    }
}