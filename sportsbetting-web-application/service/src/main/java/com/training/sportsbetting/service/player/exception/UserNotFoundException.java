package com.training.sportsbetting.service.player.exception;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -669979687973776469L;

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }

}
