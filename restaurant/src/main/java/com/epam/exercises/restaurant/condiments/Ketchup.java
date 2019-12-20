package com.epam.exercises.restaurant.condiments;

import com.epam.exercises.restaurant.food.Dish;


public class Ketchup extends CondimentDecorator {
    public Ketchup(Dish dish) {
        this.dish = dish;
    }

    @Override
    public String getDescription() {
        return dish.getDescription() + ", Ketchup";
    }

    @Override
    public double happinessIncrement(double currentHappiness) {
        return dish.happinessIncrement(currentHappiness) * 2;
    }
}
