package com.training.restaurant.domain;

public class KetchupDecorator extends FoodExtraDecorator implements Food {

    public KetchupDecorator(Food food) {
        super(food);
    }

    @Override
    public double calculateHappiness(Client client) {

        return super.food.calculateHappiness(client) * 2;

    }

    @Override
    public String toString() {
        return "food: Ketchup" + super.food.toString();
    }

}
