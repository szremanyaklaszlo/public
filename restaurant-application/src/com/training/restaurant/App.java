package com.training.restaurant;

import java.util.Arrays;
import java.util.List;

import com.training.restaurant.domain.Client;
import com.training.restaurant.domain.Order;
import com.training.restaurant.robot.CookRobot;

public class App {

    private static List<Client> clients;
    private static List<String> extrasSingle;
    private static List<String> extrasMultiple;
    private static List<Order> orders;

    public static void main(String[] args) {
        initializeData();
        CookRobot claptrap = new CookRobot();
        claptrap.addOrder(clients.get(0), orders.get(0));
        claptrap.addOrder(clients.get(1), orders.get(1));
        claptrap.processOrders();
    }

    private static void initializeData() {
        clients = Arrays.asList(new Client("Peter", 100), new Client("Berci", 200));
        extrasSingle = Arrays.asList("Ketchup");
        extrasMultiple = Arrays.asList("Mustard", "Ketchup");
        orders = Arrays.asList(new Order("Hotdog", extrasSingle), new Order("Chips", extrasMultiple));
    }

}
