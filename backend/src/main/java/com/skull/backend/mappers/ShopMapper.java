package com.skull.backend.mappers;

import com.skull.backend.dtos.ChickenDTO;
import com.skull.backend.dtos.ShopDTO;
import com.skull.backend.models.Chicken;
import com.skull.backend.models.Shop;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ShopMapper {
    @Mapping(target = "shopName", source = "name")
    @Mapping(target = "chickenDTOS", source = "chickens")
    ShopDTO map(Shop shop);

    @InheritInverseConfiguration
    Shop map(ShopDTO shop);

    List<ShopDTO> map(List<Shop> shop);

    @Mapping(target = "chickenName", source = "name")
    ChickenDTO map(Chicken shop);

    @InheritInverseConfiguration
    Chicken map(ChickenDTO shop);
}
