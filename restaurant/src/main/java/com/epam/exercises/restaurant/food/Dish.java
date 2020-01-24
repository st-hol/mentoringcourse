package com.epam.exercises.restaurant.food;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public abstract class Dish {
    protected DishType dishType;
    protected String description;

    public double happinessIncrement(double currentHappiness) {
        return currentHappiness;
    }

}
