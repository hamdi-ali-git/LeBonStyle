package com.lebonstyle.userservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RefreshScope // pour reactualiser les valeurs si la config change
@RestController
@RequestMapping("/api/users")
public class UserConfigController {
    @Value("${server.port}")
    private Long port;
    @Value("${spring.datasource.Username}")
    private String datasourceUsername;

    @GetMapping("/config")
    public Map<String, Object> getUserConfig() {
        Map<String, Object> userConfig = new HashMap<String, Object>();
        userConfig.put("port", port);
        userConfig.put("datasourceUsername", datasourceUsername);
        userConfig.put("threadName", Thread.currentThread().getName());
        return userConfig;
    }
}
