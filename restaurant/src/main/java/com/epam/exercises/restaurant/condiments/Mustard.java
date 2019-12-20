package com.epam.exercises.restaurant.condiments;

import com.epam.exercises.restaurant.food.Dish;

public class Mustard extends CondimentDecorator {
    public Mustard(Dish dish) {
        this.dish = dish;
    }

    @Override
    public String getDescription() {
        return dish.getDescription() + ", Mustard";
    }

    @Override
    public double happinessIncrement(double currentHappiness) {
        return 1;
    }
}
