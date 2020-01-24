package com.epam.exercises.restaurant.service;

import java.util.List;

import com.epam.exercises.restaurant.condiments.AddCondimentDecorator;
import com.epam.exercises.restaurant.condiments.CondimentType;
import com.epam.exercises.restaurant.exception.UnknownCondimentException;
import com.epam.exercises.restaurant.food.Dish;

public class FoodService {

    private static final String CONDIMENT_NOT_EXIST_MESSAGE = "There is no such condiment";

    public Dish decorateWithCondiments(Dish food, final List<CondimentType> condimentTypes) {
        for (CondimentType condimentType : condimentTypes) {
            switch (condimentType) {
                case KETCHUP:
                    food = new AddCondimentDecorator(food, CondimentType.KETCHUP);
                    break;
                case MUSTARD:
                    food = new AddCondimentDecorator(food, CondimentType.MUSTARD);
                    break;
                default:
                    throw new UnknownCondimentException(CONDIMENT_NOT_EXIST_MESSAGE);
            }
        }
        return food;
    }
}
