package com.skull.shop.mappers;

import com.skull.shop.dtos.ChickenDTO;
import com.skull.shop.dtos.ShopDTO;
import com.skull.shop.models.Chicken;
import com.skull.shop.models.Shop;
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
