package com.epam.training.ascii.domain;


public class CoordinateOutOfRangeException extends RuntimeException {
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    public CoordinateOutOfRangeException(final String errorMessage) {
        super(errorMessage);
    }
}
