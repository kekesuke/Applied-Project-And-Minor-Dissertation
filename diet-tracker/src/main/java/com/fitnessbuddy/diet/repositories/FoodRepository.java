package com.fitnessbuddy.diet.repositories;

import com.fitnessbuddy.diet.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Integer> {
    Optional<Food> findByfoodName(String name);
}
