package com.epam.exercises.restaurant.food;


public class HotDog extends Dish {

    public HotDog() {
        dishType = DishType.HOTDOG;
        description = dishType.getFoodName();
    }

    @Override
    public double happinessIncrement(double currentHappiness) {
        return 2;
    }
}

