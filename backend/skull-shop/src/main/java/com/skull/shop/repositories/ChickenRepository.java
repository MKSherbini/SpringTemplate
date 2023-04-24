package com.skull.shop.repositories;

import com.skull.shop.models.Chicken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChickenRepository extends JpaRepository<Chicken, Integer> {
}
