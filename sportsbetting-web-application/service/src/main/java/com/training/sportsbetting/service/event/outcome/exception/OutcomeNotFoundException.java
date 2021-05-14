package com.training.sportsbetting.service.event.outcome.exception;

public class OutcomeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -793577080715893530L;

    public OutcomeNotFoundException(String message) {
        super(message);
    }

}
