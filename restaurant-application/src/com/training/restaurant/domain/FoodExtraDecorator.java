package com.training.restaurant.domain;

public class FoodExtraDecorator {

    protected final Food food;

    public FoodExtraDecorator(Food food) {
        super();
        this.food = food;
    }

    @Override
    public String toString() {
        return "food " + food.toString();
    }

}
