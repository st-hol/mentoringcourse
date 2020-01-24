package com.epam.exercises.restaurant.food;


public class Chips extends Dish {

    public Chips() {
        dishType = DishType.CHIPS;
        description = dishType.getFoodName();
    }

    @Override
    public double happinessIncrement(double currentHappiness) {
        return currentHappiness * 0.05;
    }
}

