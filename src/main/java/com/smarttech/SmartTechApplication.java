package com.smarttech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SmartTechApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartTechApplication.class, args);
    }

}
