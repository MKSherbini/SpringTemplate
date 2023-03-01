package com.skull.backend.repositories;

import com.skull.backend.models.Chicken;
import com.skull.backend.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChickenRepository extends JpaRepository<Chicken, Integer> {
}
