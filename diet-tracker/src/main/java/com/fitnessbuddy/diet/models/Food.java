package com.fitnessbuddy.diet.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Food {

    @Id
    @SequenceGenerator(
            name = "food_id_sequence",
            sequenceName = "food_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "food_id_sequence"
    )
    private Integer foodId;
    private String foodName;
    private float calories;
    private float protein;
    private float fat;
    private float carbs;
    /* To do
     * add picture functionality 1later
     */
    private float foodWeight;
}
