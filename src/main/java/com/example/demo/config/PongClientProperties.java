package com.example.demo.config;




import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;


//@Configuration
//@ConfigurationProperties(prefix = "clients.pong")
@Data
public  class PongClientProperties {

    private String baseUrl;
    private Duration connectTimeout;
    private Duration readTimeout;

    // getter & setter
}
