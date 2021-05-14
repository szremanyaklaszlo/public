package com.training.restaurant.domain;

public class Chips implements Food {

    @Override
    public double calculateHappiness(Client client) {
        return client.getHappiness() * 0.05;
    }

    @Override
    public String toString() {
        return "[food=Chips []]";
    }

}
