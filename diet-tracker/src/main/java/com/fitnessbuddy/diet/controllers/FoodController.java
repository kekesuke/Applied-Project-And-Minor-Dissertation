package com.fitnessbuddy.diet.controllers;

import com.fitnessbuddy.clients.diet.requests.FoodRequest;
import com.fitnessbuddy.diet.services.FoodService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.FoodDto;

@RestController
@RequestMapping("api/diet")
@AllArgsConstructor
@Slf4j
public class FoodController {

    private final FoodService foodService;

    @PostMapping("/addfood")
    public void addFood(@RequestBody FoodRequest foodrequest) {
        log.info("New food added... {}", foodrequest);
        foodService.addFood(foodrequest);
    }

    @PostMapping("/foodintake")
    public ResponseEntity<?> foodIntake(@RequestBody FoodDto foodrequest) {
       return foodService.foodIntake(foodrequest);
    }
}
