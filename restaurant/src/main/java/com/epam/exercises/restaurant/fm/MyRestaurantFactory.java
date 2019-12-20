package com.epam.exercises.restaurant.fm;


import java.util.List;

import com.epam.exercises.restaurant.condiments.Ketchup;
import com.epam.exercises.restaurant.condiments.Mustard;
import com.epam.exercises.restaurant.domain.Order;
import com.epam.exercises.restaurant.food.Chips;
import com.epam.exercises.restaurant.food.Dish;
import com.epam.exercises.restaurant.food.HotDog;

public class MyRestaurantFactory extends RestaurantFactory {
    private static final String HOTDOG = "HotDog";
    private static final String CHIPS = "Chips";
    private static final String MUSTARD = "Mustard";
    private static final String KETCHUP = "Ketchup";

    public Dish makeDish(Order order) {
        System.out.println("Factory: Preparing " + order.getDishName());
        Dish food = createBase(order.getDishName());
        food = decorateWithCondiments(food, order.getCondiments());
        return food;
    }

    private Dish createBase(final String foodName) {
        switch (foodName) {
            case HOTDOG:
                return new HotDog();
            case CHIPS:
                return new Chips();
            default:
                return null;
        }
    }

    private Dish decorateWithCondiments(Dish food, final List<String> extras) {
        for (String extra : extras) {
            if (KETCHUP.equalsIgnoreCase(extra)) {
                food = new Ketchup(food);
            } else if (MUSTARD.equalsIgnoreCase(extra)) {
                food = new Mustard(food);
            }
        }
        return food;
    }

}
