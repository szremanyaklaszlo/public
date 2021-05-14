package com.tutorial.microservices.order.service;

import java.util.List;

import com.tutorial.microservices.order.data.Order;

public interface OrderCRD {

    void save(Order order);
    List<Order> getAll();
    Order getById(Long id);
    void deleteById(Long id);
    void saveAll(List<Order> order);
    
}
