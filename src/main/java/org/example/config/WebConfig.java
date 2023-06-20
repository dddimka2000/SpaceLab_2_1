package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Properties;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("/photos/**")
                .addResourceLocations("file:/C:/Users/User/IdeaProjects/SpaceLab_2_1/src/main/resources/static/img/");

        registry.addResourceHandler("/messages/**")
                .addResourceLocations("file:/C:/Users/User/IdeaProjects/SpaceLab_2_1/src/main/resources/static/files/messages/");

    }



}
