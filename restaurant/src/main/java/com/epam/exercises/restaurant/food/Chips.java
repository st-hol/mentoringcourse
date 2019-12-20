package com.epam.exercises.restaurant.food;


public class Chips extends Dish {

    public Chips() {
        description = "Chips";
    }

    @Override
    public double happinessIncrement(double currentHappiness) {
        return currentHappiness * 0.05;
    }
}

