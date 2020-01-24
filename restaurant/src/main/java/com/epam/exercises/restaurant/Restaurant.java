package com.epam.exercises.restaurant;


import com.epam.exercises.restaurant.domain.Client;
import com.epam.exercises.restaurant.domain.Order;
import com.epam.exercises.restaurant.service.ClientService;
import com.epam.exercises.restaurant.service.OrderService;


public class Restaurant {

    public static void main(String[] args) {
        ClientService clientService = new ClientService();

        Client client1 = new Client("Client1", 0);

        for (Order order : new OrderService().populateTestOrders()) {
            clientService.orderDish(client1, order);
        }

    }

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
