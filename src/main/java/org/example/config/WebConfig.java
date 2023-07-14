package org.example.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Log4j2
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${spring.pathImg}")
    String pathPhotos;
    @Value("${path.messages}")
    String pathMessages;

    @Value("${hostname}")
    String hostname;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("/photos/**")
                .addResourceLocations("file:" + pathPhotos);
        log.info("pathPhotos");

        registry.addResourceHandler("/messages/**")
                .addResourceLocations("file:" + pathMessages);

    }
}
