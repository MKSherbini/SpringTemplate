package com.skull.shop.controllers;

import com.skull.shop.dtos.ShopDTO;
import com.skull.shop.services.ShopService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public/shop")
@SecurityRequirement(name = "BearerAuth")
public class ShopController {
    private final ShopService shopService;

    @GetMapping
    public List<ShopDTO> findAll() {
        return shopService.findAll();
    }

    @GetMapping("{id}")
    public ShopDTO findById(@PathVariable int id) {
        return shopService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody @Valid ShopDTO shopDTO) {
        shopService.save(shopDTO);
    }
}
