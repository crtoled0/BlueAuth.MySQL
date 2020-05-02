package com.bz.blueauth.config;

import com.bz.blueauth.tools.AppProperties;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        AppProperties prop = AppProperties.getInstance();
        String origins = prop.get("server.allowedOrigins");
        registry.addMapping("/Auth/**")
                .allowedOrigins(origins.split(","));
    }
}