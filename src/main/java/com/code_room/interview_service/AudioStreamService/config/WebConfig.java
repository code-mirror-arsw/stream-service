package com.code_room.interview_service.AudioStreamService.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // permite todo
                .allowedOrigins("http://localhost:5173","https://victorious-water-0ec758310.2.azurestaticapps.net") // origen del frontend
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}