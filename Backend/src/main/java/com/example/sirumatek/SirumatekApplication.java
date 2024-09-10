package com.example.sirumatek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.sirumatek")
public class SirumatekApplication {
    public static void main(String[] args) {
        SpringApplication.run(SirumatekApplication.class, args);
    }
}