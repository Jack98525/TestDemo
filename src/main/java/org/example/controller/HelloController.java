package org.example.controller;

import org.example.properties.AppProperties;
import org.example.properties.DataSourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Autowired
    private AppProperties appProperties;

    @RequestMapping("/hello")
    public String hello() {
        System.out.println(dataSourceProperties);
        return "Hello World";
    }

    @GetMapping("/app-info")
    public String getAppInfo() {
        return "App Name: " + appProperties.getName() +
                ", Version: " + appProperties.getVersion() +
                ", Author: " + appProperties.getAuthor().getName() +
                ", Email: " + appProperties.getAuthor().getEmail();
    }
}
