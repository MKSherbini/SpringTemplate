package com.skull.farm.controllers;

import com.skull.farm.dtos.ChickenDTO;
import com.skull.farm.services.ChickenPublisher;
import com.skull.farm.services.FarmService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.beans.ConstructorProperties;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public/farm")
@SecurityRequirement(name = "BearerAuth")
public class FarmController {
    private final FarmService farmService;
    private final ChickenPublisher chickenPublisher;
    @Qualifier("rabbitTemplateJson")
    private final RabbitTemplate rabbitTemplate;

    @PostMapping
    public String sendMessage(String message) {
        final ChickenDTO chicken = ChickenDTO.builder().chickenName(message).size(10).build();
        rabbitTemplate.convertAndSend("skull-chicken-exchange",
                "chicken.tasty", chicken);
//        chickenPublisher.send(chicken);
        return message;
    }
}
