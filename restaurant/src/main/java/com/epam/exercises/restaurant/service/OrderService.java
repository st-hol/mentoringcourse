package com.epam.exercises.restaurant.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.epam.exercises.restaurant.condiments.CondimentType;
import com.epam.exercises.restaurant.domain.Order;
import com.epam.exercises.restaurant.food.DishType;

public class OrderService {

    public List<Order> populateTestOrders() {
        Order order1 = new Order(DishType.HOTDOG, Collections.singletonList(CondimentType.KETCHUP));
        Order order2 = new Order(DishType.CHIPS, Collections.singletonList(CondimentType.MUSTARD));
        Order order3 = new Order(DishType.HOTDOG, Arrays.asList(CondimentType.KETCHUP, CondimentType.MUSTARD));
        Order order4 = new Order(DishType.CHIPS, Arrays.asList(CondimentType.KETCHUP, CondimentType.KETCHUP));
        Order order5 = new Order(DishType.CHIPS, Collections.emptyList());
        Order order6 = new Order(DishType.HOTDOG, Collections.emptyList());

        return Arrays.asList(order1, order2, order3, order4, order5, order6);
    }
}
