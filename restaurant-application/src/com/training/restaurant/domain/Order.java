package com.training.restaurant.domain;

import java.util.List;

import com.training.restaurant.observer.Observable;

public class Order extends Observable<Food> {

    private final String food;
    private final List<String> extras;

    public Order(String food, List<String> extras) {
        super();
        this.food = food;
        this.extras = extras;
    }

    public String getFood() {
        return food;
    }

    public List<String> getExtras() {
        return extras;
    }

    @Override
    public String toString() {
        return "Order [food=" + food + ", extras=" + extras + "]";
    }

}
