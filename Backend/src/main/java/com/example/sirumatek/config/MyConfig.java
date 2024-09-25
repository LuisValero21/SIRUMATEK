package com.example.sirumatek.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mi")
public class MyConfig {

    private String secret_key;
    private String front_url;

    // Getters y setters
    public String getSecret_key() {
        return secret_key;
    }

    public void setSecret_key(String variable) {
        this.secret_key = variable;
    }

    public String getFront_url() {
        return front_url;
    }

    public void setFront_url(String front_url) {
        this.front_url = front_url;
    }
}
