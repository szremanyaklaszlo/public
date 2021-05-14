package com.tutorial.microservices.order.service.exception;

public class OrderNotFoundException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1748715685531175938L;

    public OrderNotFoundException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public OrderNotFoundException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

}
