package com.epam.exercises.restaurant.food;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Dish {
    String description = "Unknown";
    private String name;


    public Dish() {
        //required
    }

    public double happinessIncrement(double currentHappiness) {
        return currentHappiness;
    }

}
