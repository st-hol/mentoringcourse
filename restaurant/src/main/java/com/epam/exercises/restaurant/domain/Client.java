package com.epam.exercises.restaurant.domain;

import com.epam.exercises.restaurant.food.Dish;
import com.epam.exercises.restaurant.observer.Observer;

import lombok.Data;

@Data
public class Client implements Observer<Client> {
    private String name;
    private double happiness;

    public Client(String name, double happiness) {
        this.name = name;
        this.happiness = happiness;
    }

    private void eat(Dish dish) {
        System.out.println("Before eating: " + name + "(" + happiness + ")" + " is eating " + dish.getDescription());
        happiness += dish.happinessIncrement(happiness);
        System.out.println("After eating: " + name + "(" + happiness + ")");
    }

    public void update(Dish dish) {
        eat(dish);
        System.out.println("\n\n\n");
    }

}
