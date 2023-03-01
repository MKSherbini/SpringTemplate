package com.skull.backend.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ShopDTO {
    private String shopName;
    private List<ChickenDTO> chickenDTOS;
}
