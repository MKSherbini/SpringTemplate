package com.skull.shop.models;

import com.skull.shop.enums.ChickenType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Chicken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int size;
    @Enumerated(EnumType.STRING)
    private ChickenType chickenType;

    @ManyToOne
    private Shop shop;
}
