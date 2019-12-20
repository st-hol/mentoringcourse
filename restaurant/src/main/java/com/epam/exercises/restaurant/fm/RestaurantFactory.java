package com.epam.exercises.restaurant.fm;

import com.epam.exercises.restaurant.domain.Order;
import com.epam.exercises.restaurant.food.Dish;


public abstract class RestaurantFactory {

    abstract Dish makeDish(Order order);

    public Dish orderDish(Order order) {
        return makeDish(order);
    }

}