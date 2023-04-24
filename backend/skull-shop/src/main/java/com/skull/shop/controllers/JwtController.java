package com.skull.shop.controllers;

import com.skull.shop.services.JwtProvider;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public/token")
@SecurityRequirement(name = "BearerAuth")
public class JwtController {
    private final JwtProvider jwtProvider;

    @PostMapping
    public Map<String, String> generateToken(@RequestBody Map<String, Object> claims) {
        return Map.of("token", jwtProvider.generateToken(claims));
    }

    @GetMapping
    public Map<String, Object> getInfo() {
         return jwtProvider.getInfo();
    }
}
