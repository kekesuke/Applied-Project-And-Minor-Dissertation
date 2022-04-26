package utils;

import com.fitnessbuddy.diet.repositories.models.Food;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FoodDto {
    private List<Food> food;

}
