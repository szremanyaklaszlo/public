package com.training.restaurant.robot;

import java.util.LinkedList;
import java.util.Queue;

import com.training.restaurant.domain.Client;
import com.training.restaurant.domain.Order;
import com.training.restaurant.factory.FoodFactory;

public class CookRobot {

    private Queue<Order> orders = new LinkedList<>();

    public CookRobot() {
    }

    public void addOrder(Client client, Order order) {
        orders.add(order);
        order.addObserver(client);
        System.out.println("Order registered, client: " + client.toString() + ", order: " + order.toString() + ".");
    }

    public void processOrders() {
        System.out.println("CookRobot: Processing " + orders.size() + " order(s)...");
        while (!orders.isEmpty()) {
            Order order = orders.remove();
            System.out.println("Order: Notifying observers of " + order.toString() + "...");
            order.notifyObservers(FoodFactory.createFood(order));
            System.out.println("Order: Notification done.");
        }
    }

}
