package com.skull.farm.services;

import com.skull.farm.dtos.ChickenDTO;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface ChickenPublisher {
    @Gateway(requestChannel = "jsonConversionChannel")
    void send(ChickenDTO chicken);
}
