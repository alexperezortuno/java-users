package com.glign.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
public class AuditorConfig {
    @Value("${userApi.application.user}")
    private String userApplication;

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.of(userApplication);
    }
}
