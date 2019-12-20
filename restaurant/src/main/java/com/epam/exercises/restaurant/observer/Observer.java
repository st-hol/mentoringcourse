package com.epam.exercises.restaurant.observer;


import com.epam.exercises.restaurant.food.Dish;

public interface Observer<E> {
    void update(Dish food);
}
