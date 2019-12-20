package com.epam.exercises.restaurant.observer;

import java.util.ArrayList;
import java.util.List;

import com.epam.exercises.restaurant.food.Dish;


public abstract class Observable<E> {
    private List<Observer<E>> observers = new ArrayList<>();

    public boolean subscribe(Observer<E> observer) {
        return observers.add(observer);
    }

    public void notifyObservers(Dish dish) {
        for (Observer observer : observers) {
            observer.update(dish);
        }
    }
}
