package com.example.sirumatek.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.example.sirumatek.service", "com.example.sirumatek.repository", "com.example.sirumatek.controller"})
public class AppConfig {
    // Configuraciones adicionales si es necesario
}
