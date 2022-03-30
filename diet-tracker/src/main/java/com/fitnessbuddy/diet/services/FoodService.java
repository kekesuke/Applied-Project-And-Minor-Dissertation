package com.fitnessbuddy.diet.services;


import com.fitnessbuddy.clients.diet.requests.FoodRequest;
import com.fitnessbuddy.diet.models.Food;
import com.fitnessbuddy.diet.repositories.FoodRepository;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class FoodService{
    private final FoodRepository foodRepository;

    public void addFood(FoodRequest foodRequest) {
        foodRepository.save(
                Food.builder()
                        .foodWeight(foodRequest.foodWeight())
                        .foodName(foodRequest.foodName())
                        .protein(foodRequest.protein())
                        .calories(foodRequest.calories())
                        .carbs(foodRequest.carbs())
                        .fat(foodRequest.fat())
                        .build()
        );

    }

    public ResponseEntity<?> foodIntake(FoodRequest foodRequest) {
        try {
            Food food = findByName(foodRequest.foodName());
            food.setCalories(food.getCalories() * foodRequest.foodWeight());//Calories intake user
            food.setProtein(food.getProtein() * foodRequest.foodWeight());//Protein intake user
            food.setCarbs(food.getCarbs() * foodRequest.foodWeight());//Carbs intake user
            food.setFat(food.getFat() * foodRequest.foodWeight());//Fat intake user
            food.setFoodWeight(foodRequest.foodWeight());//take the user qty entered
            return ResponseEntity.ok(food);

        }catch (NotFoundException e) {
            return ResponseEntity.badRequest()
                    .body(new NotFoundException(e.getMessage()));   //create custom exception handler later
        }
    }
    @Transactional
    public Food findByName(String name) throws NotFoundException {
        Food food = foodRepository.findByfoodName(name)
                .orElseThrow(() -> new NotFoundException("No  food found with name: " + name));
        return food;
    }

}
