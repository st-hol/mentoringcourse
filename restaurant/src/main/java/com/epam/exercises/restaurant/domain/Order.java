package com.epam.exercises.restaurant.domain;

import java.util.List;

import com.epam.exercises.restaurant.food.Dish;
import com.epam.exercises.restaurant.observer.Observable;

import lombok.Data;


@Data
public class Order extends Observable<Client> {
    private final String dishName;
    private final List<String> condiments;

    public Order(String dishName, List<String> condiments) {
        this.dishName = dishName;
        this.condiments = condiments;
    }

    @Override
    public void notifyObservers(Dish dish) {
        System.out.println("[Observable order notification]");
        super.notifyObservers(dish);
    }

}

