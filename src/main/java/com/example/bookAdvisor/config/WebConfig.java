package com.example.bookAdvisor.config;

import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Sirve imágenes desde ./portadas (externa) y como fallback desde classpath:/static/portadas/
        String externalPortadas = Paths.get("portadas").toAbsolutePath().toUri().toString();
        registry.addResourceHandler("/portadas/**")
                .addResourceLocations(externalPortadas, "classpath:/static/portadas/");
    }

}
