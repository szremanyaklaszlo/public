package com.epam.training.ascii.domain;

import java.awt.image.BufferedImage;

public final class Image {

    private static final int MAX_BYTE_VALUE = 0xFF;
    private static final int BYTE = 8;
    private static final int TWO_BYTES = 16;

    private final BufferedImage bufferedImage;

    public Image(final BufferedImage image) {
        bufferedImage = image;
    }

    public int getHeight() {
        return bufferedImage.getHeight();
    }

    public int getWidth() {
        return bufferedImage.getWidth();
    }

    public int getIntensity(final Coordinate coordinate) {
        return getRed(coordinate) + getBlue(coordinate) + getGreen(coordinate);
    }

    private int getRed(final Coordinate coordinate) {
        return getRgbValue(coordinate) >> TWO_BYTES & MAX_BYTE_VALUE;
    }

    private int getGreen(final Coordinate coordinate) {
        return getRgbValue(coordinate) >> BYTE & MAX_BYTE_VALUE;
    }

    private int getBlue(final Coordinate coordinate) {
        return getRgbValue(coordinate) & MAX_BYTE_VALUE;
    }

    private int getRgbValue(final Coordinate coordinate) {
        if (isHorizontalOutOfRange(coordinate)) {
            throw new CoordinateOutOfRangeException("Coordinate X out of range. X coordinate pozition: " + coordinate.getX() + " Image Width: " + getWidth());
        }
        if (isVerticalOutOfRange(coordinate)) {
            throw new CoordinateOutOfRangeException("Coordinate Y out of range: Y coordinate pozition: " + coordinate.getY() + " Image Height: " + getHeight());
        }
        return bufferedImage.getRGB(coordinate.getX(), coordinate.getY());
    }

    private boolean isHorizontalOutOfRange(final Coordinate coordinate) {
        return coordinate.getX() < 0 || coordinate.getX() > getWidth();
    }

    private boolean isVerticalOutOfRange(final Coordinate coordinate) {
        return coordinate.getY() < 0 || coordinate.getY() > getHeight();
    }

}
