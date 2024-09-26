package com.example.sirumatek.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
public class MyConfig {

    private String secret_key;
    private String front_url;

    // Getters y setters
    public String getSecret_key() {
        if (secret_key == null || secret_key.isEmpty()) {
            throw new IllegalStateException("Secret key is not set or is empty");
        }
        return secret_key;
    }

    public void setSecret_key(String secret_key) {
        this.secret_key = secret_key;
    }

    public String getFront_url() {
        if (front_url == null || front_url.isEmpty()) {
            throw new IllegalStateException("Front URL is not set or is empty");
        }
        return front_url;
    }

    public void setFront_url(String front_url) {
        this.front_url = front_url;
    }
}
