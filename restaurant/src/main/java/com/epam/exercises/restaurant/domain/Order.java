package com.epam.exercises.restaurant.domain;

import java.util.List;

import com.epam.exercises.restaurant.condiments.CondimentType;
import com.epam.exercises.restaurant.food.Dish;
import com.epam.exercises.restaurant.food.DishType;
import com.epam.exercises.restaurant.observer.Observable;

import lombok.Data;

@Data
public class Order extends Observable<Client> {
    private final DishType dishType;
    private final List<CondimentType> condimentTypes;

    public Order(DishType dishType, List<CondimentType> condimentTypes) {
        this.dishType = dishType;
        this.condimentTypes = condimentTypes;
    }

    @Override
    public void notifyObservers(Dish dish) {
        System.out.println("[Observable order notification]");
        super.notifyObservers(dish);
    }

}

