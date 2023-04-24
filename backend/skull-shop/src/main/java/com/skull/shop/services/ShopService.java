package com.skull.shop.services;

import com.skull.shop.configs.RabbitMQConfig;
import com.skull.shop.dtos.ChickenDTO;
import com.skull.shop.dtos.ShopDTO;
import com.skull.shop.exceptions.ResourceNotFoundException;
import com.skull.shop.models.Shop;
import com.skull.shop.repositories.ShopRepository;
import com.skull.shop.mappers.ShopMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.MediaType;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

    //        @PreAuthorize("#id == @jwtProvider.getInfo().get('id')")
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


    // DO NOT do this
//    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
//    public void restock(Message message) {
//        log.info("message body: " + new String(message.getBody()));
//        log.info("message headers: " + message.getMessageProperties());
//    }

    // message vs annotations, better to know less about messaging system and allow easy invoking
    // @Payload String message, @Headers Map<String, Object> headers, @Header("contentType") MediaType mediaType
//        throw new AmqpRejectAndDontRequeueException("die");
//    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
//    public void restock(ChickenDTO chicken) {
//        log.info("chicken: " + chicken);
//    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void restock2(String chicken) {
        log.info("chicken: " + chicken);
        throw new RuntimeException();
    }

    //    @RabbitListener(queues = RabbitMQConfig.DEAD_QUEUE_NAME, containerFactory = "rabbitListenerContainerFactory")
//    @RabbitListener(queues = RabbitMQConfig.DEAD_QUEUE_NAME)
//    public void handleDead(ChickenDTO message) {
//        log.info("dead message: " + message);
//    }
}
