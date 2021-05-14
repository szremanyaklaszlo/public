package com.training.sportsbetting.service.event.exception;

public class SportEventNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -544814440483812704L;
    
    public SportEventNotFoundException() {
        super();
    }
    
    public SportEventNotFoundException(String msg) {
        super(msg);
    }

}
