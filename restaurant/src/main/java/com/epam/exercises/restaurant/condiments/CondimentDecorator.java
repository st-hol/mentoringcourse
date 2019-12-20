package com.epam.exercises.restaurant.condiments;

import com.epam.exercises.restaurant.food.Dish;

public abstract class CondimentDecorator extends Dish {

    protected Dish dish;

    public abstract String getDescription();

    public abstract double happinessIncrement(double currentHappiness);

}
