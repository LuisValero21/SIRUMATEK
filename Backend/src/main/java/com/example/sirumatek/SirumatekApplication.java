package com.example.sirumatek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.example.sirumatek")
@Configuration
@EnableScheduling
public class SirumatekApplication {
    public static void main(String[] args) {
        SpringApplication.run(SirumatekApplication.class, args);
    }
}