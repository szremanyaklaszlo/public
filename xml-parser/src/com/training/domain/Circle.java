package com.training.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Circle {

    private final static List<Character> RGB_VALUE_CASE = Arrays.asList('a', 'b', 'c', 'd', 'f', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
    private final static int HIGTH = 800;
    private final static int WIDTH= 1700;
    private HashMap<String, String> existStates = new HashMap<>();
    private double latitude;
    private double longitude;
    private String stateColorCode;

    public Circle(double latitude, double longitude, String state) {
        this.latitude = calculateHight(latitude);
        this.longitude = calculateWidth(longitude);
        this.stateColorCode = calculateFill(state);
    }

    private double calculateHight(double latitude) {
        return HIGTH - (latitude * 10);
    }

    private double calculateWidth(double longitude) {
        return (longitude * 10) + WIDTH;
    }

    private String calculateFill(String state) {
        if (!existStates.containsKey(state)) {
            existStates.put(state, createNewColorCode());
        }
        return existStates.get(state);
    }

    private String createNewColorCode() {
        StringBuilder colorCode = new StringBuilder();
        Random rand = new Random();
        colorCode.append("#");
        for (int i = 0; i < 6; i++) {
            colorCode.append(RGB_VALUE_CASE.get(rand.nextInt(RGB_VALUE_CASE.size())));
        }
        return colorCode.toString();
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getStateColorCode() {
        return stateColorCode;
    }

}
