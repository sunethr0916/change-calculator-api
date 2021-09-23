package com.adp.sample.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ServiceConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/help/v2/api-docs", "/v2/api-docs");
        registry.addRedirectViewController("/help/swagger-resources", "/swagger-resources");
        registry.addRedirectViewController("/help", "/swagger-ui.html");
        registry.addRedirectViewController("/help/", "/swagger-ui.html");
        registry.addRedirectViewController("/health", "/actuator/health");
        registry.addRedirectViewController("/health/", "/actuator/health");
        registry.addRedirectViewController("/info", "/actuator/info");
        registry.addRedirectViewController("/info/", "/actuator/info");
    }
}