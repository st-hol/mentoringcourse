package com.epam.exercises.restaurant.food;


public class HotDog extends Dish {
    public HotDog() {
        description = "HotDog";
    }

    @Override
    public double happinessIncrement(double currentHappiness) {
        return 2;
    }
}

