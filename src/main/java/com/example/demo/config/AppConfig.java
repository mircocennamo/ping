package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author mirco.cennamo on 22/10/2024
 * @project ping
 */
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    private String message;
}
