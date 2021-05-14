package com.training.restaurant.domain;

import com.training.restaurant.observer.Observer;

public class Client implements Observer<Food> {

    private String name;
    private double happiness;

    public Client(String name, double happiness) {
        super();
        this.name = name;
        this.happiness = happiness;
    }

    public double getHappiness() {
        return happiness;
    }

    public void consume(Food food) {
        System.out.println("Client: Starting to eat food, client: " + this.toString() + ", " + food.toString() + "...");
        System.out.println("Client: Csam csam nyam nyam");
    }

    @Override
    public void update(Food food) {
        consume(food);
        happiness += food.calculateHappiness(this);
        System.out.println("Client: Food eaten, client: " + this.toString());
    }

    @Override
    public String toString() {
        return "Client [name=" + name + ", happiness=" + happiness + "]";
    }

}
