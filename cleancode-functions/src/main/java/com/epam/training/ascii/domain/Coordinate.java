package com.epam.training.ascii.domain;

public class Coordinate {

    private final int xCoordinate;
    private final int yCoordinate;

    public Coordinate(final int xCoordinate, final int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public int getX() {
        return xCoordinate;
    }

    public int getY() {
        return yCoordinate;
    }

}
