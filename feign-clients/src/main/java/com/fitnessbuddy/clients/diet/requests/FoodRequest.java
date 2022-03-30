package com.fitnessbuddy.clients.diet.requests;

public record FoodRequest(
        String foodName,
        float calories,
        float protein,
        float carbs,
        float fat,
        float foodWeight
){}
