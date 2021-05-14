package com.epam.training.ascii.service;

import java.util.Arrays;

import com.epam.training.ascii.domain.Coordinate;
import com.epam.training.ascii.domain.Image;

public class ImageConverter {

    private static final char[] CHARS_OF_DARKNESS = { '#', '@', 'X', 'L', 'I', ':', '.', ' ' };
    private int stepY;
    private int stepX;
    private Integer maxIntensity;
    private Integer minIntensity;

    private final Image image;

    public ImageConverter(Image image) {
        this.image = image;
    }

    public char[][] convertImageToCharacterMatrix(int resolutionX, int resolutionY) {
        int[][] intensityMatrix = createIntensityMatrix(resolutionX, resolutionY);
        return calculateCharacterMatrix(intensityMatrix);
    }

    private int[][] createIntensityMatrix(int resolutionX, int resolutionY) {
        if (isGivenResolutionMoreThanOriginalPicture(resolutionX, resolutionY)) {
            throw new IllegalArgumentException("Resolution is more than original image. Image width: "
                    + image.getWidth() + " given width: " + resolutionX
                    + " Image height: " + image.getHeight() + " given height: " + resolutionY);
        }
        return calculateIntensityMatrix(resolutionX, resolutionY);
    }

    private boolean isGivenResolutionMoreThanOriginalPicture(int resolutionX, int resolutionY) {
        return resolutionX > image.getWidth() || resolutionY > image.getHeight();
    }

    private int[][] calculateIntensityMatrix(int resolutionX, int resolutionY) {
        int[][] intensityMatrix = new int[resolutionY][resolutionX];
        int intensityCoordinateY = 0;
        int intensityCoordinateX = 0;
        calculateStepSize(resolutionY, resolutionX);
        for (int y = 0; y < resolutionY * stepY; y += stepY, intensityCoordinateY++) {
            for (int x = 0; x < resolutionX * stepX; x += stepX, intensityCoordinateX++) {
                intensityMatrix[intensityCoordinateY][intensityCoordinateX] = calculateAverageAreaIntensity(y, x);
            }
            intensityCoordinateX = 0;
        }
        return intensityMatrix;
    }

    private void calculateStepSize(int resolutionY, int resolutionX) {
        stepY = image.getHeight() / resolutionY;
        stepX = image.getWidth() / resolutionX;
    }

    private Integer calculateAverageAreaIntensity(final int startY, final int startX) {
        int sum = 0;
        for (int y = startY; y < startY + stepY; y++) {
            for (int x = startX; x < startX + stepX; x++) {
                sum += image.getIntensity(new Coordinate(x, y));
            }
        }
        return sum / (stepY * stepX);
    }

    private void initializeIntensityRange(int[][] intensityMatrix) {
        maxIntensity = Arrays.stream(intensityMatrix).flatMapToInt(Arrays::stream).max().orElseThrow();
        minIntensity = Arrays.stream(intensityMatrix).flatMapToInt(Arrays::stream).min().orElseThrow();
    }

    private char[][] calculateCharacterMatrix(int[][] intensityMatrix) {
        initializeIntensityRange(intensityMatrix);
        return transformIntensityMatrixToCharacterMatrix(intensityMatrix);
    }

    private char[][] transformIntensityMatrixToCharacterMatrix(int[][] intensityMatrix) {
        char[][] characterMatrix = new char[intensityMatrix.length][intensityMatrix[0].length];
        for (int y = 0; y < characterMatrix.length; y++) {
            for (int x = 0; x < characterMatrix[y].length; x++) {
                characterMatrix[y][x] = charByDarkness(intensityMatrix[y][x]);
            }
        }
        return characterMatrix;
    }

    private char charByDarkness(final Integer intensity) {
        return CHARS_OF_DARKNESS[(intensity - minIntensity) * CHARS_OF_DARKNESS.length
                                 / (maxIntensity - minIntensity + 1)];
    }
}
