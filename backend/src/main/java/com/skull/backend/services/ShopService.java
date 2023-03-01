package com.skull.backend.services;

import com.skull.backend.dtos.ShopDTO;
import com.skull.backend.exceptions.ResourceNotFoundException;
import com.skull.backend.models.Shop;
import com.skull.backend.repositories.ShopRepository;
import com.skull.backend.mappers.ShopMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;
    private final ShopMapper shopMapper;

    public List<ShopDTO> findAll() {
        return shopMapper.map(
                shopRepository.findAll()
        );
    }

    public void save(ShopDTO shopDTO) {
        final Shop shop = shopMapper.map(shopDTO);
        shop.getChickens()
                .forEach(chicken -> chicken.setShop(shop));
        shopRepository.save(shop);
    }

    //    @PreAuthorize("#id == @jwtProvider.getInfo().get('id')")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PreAuthorize("hasRole('ADMIN')")
//    @PostAuthorize("returnObject.shopName==@jwtProvider.getInfo().get('name')")
    public ShopDTO findById(int id) {
        final Optional<Shop> optionalShop = shopRepository.findById(id);
        if (optionalShop.isEmpty()) {
            throw new ResourceNotFoundException(id);
        }
        return shopMapper.map(optionalShop.get());
    }

//    {
//        ChickenDTO d = ChickenDTO.builder()
//                .size(9)
//                .chickenName("asd")
//                .build();
//
//    }
}
