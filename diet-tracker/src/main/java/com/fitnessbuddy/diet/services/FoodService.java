package com.fitnessbuddy.diet.services;


import com.fitnessbuddy.clients.diet.requests.FoodRequest;
import com.fitnessbuddy.diet.repositories.models.Food;
import com.fitnessbuddy.diet.repositories.FoodRepository;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import utils.FoodDto;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
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

    public ResponseEntity<?> foodIntake(FoodDto foodRequest) {
        log.info("test");
//        try {
//            Food food = findByName(foodRequest.foodName());
//            food.setCalories(food.getCalories() * foodRequest.foodWeight());//Calories intake user
//            food.setProtein(food.getProtein() * foodRequest.foodWeight());//Protein intake user
//            food.setCarbs(food.getCarbs() * foodRequest.foodWeight());//Carbs intake user
//            food.setFat(food.getFat() * foodRequest.foodWeight());//Fat intake user
//            food.setFoodWeight(foodRequest.foodWeight());//take the user qty entered
            List<Food> listFood = new ArrayList<>();
             foodRequest.getFood().forEach( food -> {
                 log.info(food.getFoodName());
                 try {
                     Food currentFood = findByName(food.getFoodName().toLowerCase());
                     currentFood.setCalories(currentFood.getCalories() * food.getFoodWeight());//Calories intake user
                     currentFood.setProtein(currentFood.getProtein() * food.getFoodWeight());//Protein intake user
                     currentFood.setCarbs(currentFood.getCarbs() * food.getFoodWeight());//Carbs intake user
                     currentFood.setFat(currentFood.getFat() * food.getFoodWeight());//Fat intake user
                     currentFood.setFoodWeight(food.getFoodWeight());//take the user qty entered
                     listFood.add(currentFood);;
                 } catch (NotFoundException e) {
                     e.printStackTrace();
                 }

            });
            return ResponseEntity.ok(listFood);

//        }catch (NotFoundException e) {
//            return ResponseEntity.badRequest()
//                    .body(new NotFoundException(e.getMessage()));   //create custom exception handler later
//        }
    }

    public Food findByName(String name) throws NotFoundException {
        Food food = foodRepository.findByfoodName(name)
                .orElseThrow(() -> new NotFoundException("No  food found with name: " + name));
        return food;
    }

}
