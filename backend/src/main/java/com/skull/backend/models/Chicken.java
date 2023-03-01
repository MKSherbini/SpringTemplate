package com.skull.backend.models;

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

    @ManyToOne
    private Shop shop;
}
