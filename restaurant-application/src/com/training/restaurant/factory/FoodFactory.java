package com.training.restaurant.factory;

import java.util.List;

import com.training.restaurant.domain.Chips;
import com.training.restaurant.domain.Food;
import com.training.restaurant.domain.Hotdog;
import com.training.restaurant.domain.KetchupDecorator;
import com.training.restaurant.domain.MustardDecorator;
import com.training.restaurant.domain.Order;

public class FoodFactory {

    private final static String HOTDOG = "Hotdog";
    private final static String CHIPS = "Chips";
    private final static String MUSTARD = "Mustard";
    private final static String KETCHUP = "Ketchup";

    public FoodFactory() {
    };

    public static Food createFood(Order order) {
        System.out.println("FoodFactory: Preparing food, order: " + order.toString() + "...");
        return addExtras(createMainFood(order.getFood()), order.getExtras());
    }

    private static Food createMainFood(String mainFood) {
        Food food;
        switch (mainFood) {
        case HOTDOG:
            food = new Hotdog();
            break;
        case CHIPS:
            food = new Chips();
            break;
        default:
            throw new IllegalArgumentException("Invalid main food - " + mainFood);
        }
        return food;
    }

    private static Food addExtras(Food food, List<String> extras) {
        if (extras.size() > 0) {
            for (String extra : extras) {
                switch (extra) {
                case KETCHUP:
                    food = new KetchupDecorator(food);
                    break;
                case MUSTARD:
                    food = new MustardDecorator(food);
                    break;
                }
            }
        }
        System.out.println("FoodFactory: Food prepared, " + food.toString());
        return food;
    }

}
