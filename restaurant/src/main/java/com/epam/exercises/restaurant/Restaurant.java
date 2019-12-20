package com.epam.exercises.restaurant;


import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import com.epam.exercises.restaurant.domain.Client;
import com.epam.exercises.restaurant.domain.Order;
import com.epam.exercises.restaurant.fm.MyRestaurantFactory;
import com.epam.exercises.restaurant.fm.RestaurantFactory;
import com.epam.exercises.restaurant.food.Dish;


public class Restaurant {

    private final Queue<Order> orders = new LinkedList<>();
    private final RestaurantFactory restaurantFactory = new MyRestaurantFactory();

    private Restaurant() {
    }

    /**
     * <p>Conversation</p>
     * Simulate the workflow of a restaurant. We expect new products and extras in the future. The marketing department
     * is very inventive about possible products and extra effects.
     *
     * <p>Workflow</p>
     * <p>
     * The client orders a product
     * The orders are prepared by a robot one after another (FIFO)
     * The client receives and consumes the products
     *
     * <p>Information about the products</p>
     * The restaurant sells hot dogs and chips
     * Extras for the products: ketchup, mustard
     * Hot dog increases client happiness by 2
     * Chips increases client happiness by 5%
     * Ketchup doubles the effect of a product
     * Mustard increases client happiness by 1 and removes the effect of the product
     *
     *
     *
     *
     * <p>Confirmation</p>
     * The restaurant processes the orders one after another. Each order has a client and a food type. When the food is
     * ready, the client consumes it and it affects their happiness. The client knows when the food is ready through a
     * notification from the observer. The dishes are created with a factory, and they can have extras using a
     * decorator.
     */
    public static void main(String[] args) {
        Order order1 = new Order("HotDog", Collections.singletonList("Ketchup"));
        Order order2 = new Order("Chips", Collections.singletonList("Mustard"));
        Order order3 = new Order("HotDog", Arrays.asList("Ketchup", "Mustard"));
        Order order4 = new Order("Chips", Arrays.asList("Ketchup", "Ketchup"));
        Order order5 = new Order("Chips", Collections.emptyList());
        Order order6 = new Order("HotDog", Collections.emptyList());

        Client client1 = new Client("Client1", 0);
        Client client2 = new Client("Client2", 10);

        Restaurant restaurant = new Restaurant();
        restaurant.orderDish(client1, order1);
        restaurant.orderDish(client1, order2);
        restaurant.orderDish(client2, order3);
        restaurant.orderDish(client2, order4);
        restaurant.orderDish(client1, order5);
        restaurant.orderDish(client2, order6);

        restaurant.processOrders();
    }

    private void orderDish(Client client, Order order) {
        order.subscribe(client);
        orders.add(order);
    }

    private void processOrders() {
        orders.forEach(order -> {
            Dish food = restaurantFactory.orderDish(order);
            order.notifyObservers(food);
        });
        orders.clear();
    }

}
