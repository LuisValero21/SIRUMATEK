package com.example.sirumatek.controller;

import com.example.sirumatek.config.MyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {

    private final MyConfig myConfig;

    @Autowired
    public MyController(MyConfig myConfig) {
        this.myConfig = myConfig;
    }

    @GetMapping("/info")
    public String getInfo() {
        return "URL del Servicio: " + myConfig.getFront_url();
    }

    @GetMapping("/secure-data")
    public String getSecureData() {
        return "This is a secured endpoint";
    }
}