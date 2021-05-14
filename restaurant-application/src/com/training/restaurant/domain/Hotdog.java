package com.training.restaurant.domain;

public class Hotdog implements Food {

    @Override
    public String toString() {
        return "[food=Hotdog []]";
    }

    @Override
    public double calculateHappiness(Client client) {

        return 2;

    }

}
