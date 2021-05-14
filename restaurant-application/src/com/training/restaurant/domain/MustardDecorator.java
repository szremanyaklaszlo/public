package com.training.restaurant.domain;

public class MustardDecorator extends FoodExtraDecorator implements Food {

    public MustardDecorator(Food food) {
        super(food);
    }

    @Override
    public double calculateHappiness(Client client) {
        return 1;
    }

    @Override
    public String toString() {
        return "food: Mustard" + super.food.toString();
    }

}
