package com.smarttech.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestfulConfig {

    @Bean
    public RestTemplate defaultRestTemplate() {
        return new RestTemplateBuilder()
                .setReadTimeout(Duration.ofMinutes(1))
                .build();
    }
}
