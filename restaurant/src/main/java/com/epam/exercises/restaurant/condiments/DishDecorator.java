package com.epam.exercises.restaurant.condiments;

import com.epam.exercises.restaurant.food.Dish;
import com.epam.exercises.restaurant.food.DishType;

public abstract class DishDecorator extends Dish {

    protected Dish decoratedDish;

    public DishDecorator(Dish decoratedDish) {
        super();
        this.decoratedDish = decoratedDish;
    }

    public DishType getDishType() {
        return dishType;
    }

    public abstract double happinessIncrement(double currentHappiness);

}
