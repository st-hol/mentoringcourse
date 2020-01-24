package com.epam.exercises.restaurant.service;

import com.epam.exercises.restaurant.domain.Client;
import com.epam.exercises.restaurant.domain.Order;
import com.epam.exercises.restaurant.fm.RestaurantFactory;
import com.epam.exercises.restaurant.fm.RestaurantFactoryImpl;
import com.epam.exercises.restaurant.food.Dish;

public class ClientService {

    private final RestaurantFactory restaurantFactory = new RestaurantFactoryImpl();

    public void orderDish(Client client, Order order) {
        order.subscribe(client);
        Dish food = restaurantFactory.orderDish(order);
        order.notifyObservers(food);
    }

}
