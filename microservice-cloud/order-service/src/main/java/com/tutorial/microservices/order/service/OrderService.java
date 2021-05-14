package com.tutorial.microservices.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutorial.microservices.order.data.Order;
import com.tutorial.microservices.order.data.OrderRepository;
import com.tutorial.microservices.order.service.exception.OrderNotFoundException;

@Service
public class OrderService implements OrderCRD {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void save(Order order) {
        if (order == null) {
            throw new NullPointerException("Order must not be null.");
        }
        orderRepository.save(order);
    }

    @Override
    public void saveAll(List<Order> orders) {
        orders.forEach(order -> {
            if (order == null)
                throw new NullPointerException("List of orders must not contain null element.");
        });
        orderRepository.saveAll(orders);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order getById(Long id) {
        if (id == null) {
            throw new NullPointerException("Id must not be null.");
        }
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order has not found by id " + id + "."));
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new NullPointerException("Id must not be null.");
        }
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("Order has not found by id " + id + ".");
        }
        orderRepository.deleteById(id);
    }

}
