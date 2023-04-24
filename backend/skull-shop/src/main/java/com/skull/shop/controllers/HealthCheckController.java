package com.skull.shop.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/public/health")
public class HealthCheckController {
    @GetMapping
    public Map<String, String> health() {
        return Map.of("health", "alive");
    }
}
