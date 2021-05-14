package com.tutorial.microservices.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.microservices.order.data.Order;
import com.tutorial.microservices.order.service.OrderCRD;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderCRD orderService;

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAll() {
        return ResponseEntity.ok(orderService.getAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable ("id") Long id) {
        return ResponseEntity.ok(orderService.getById(id));
    }
    
    @PostMapping(path = "/save", consumes = "application/json")
    public HttpStatus save(@RequestBody Order order) {
        orderService.save(order);
        return HttpStatus.CREATED;
    }
    
    @PostMapping("/saveAll")
    public HttpStatus saveAll(@RequestBody List<Order> order) {
        orderService.saveAll(order);
        return HttpStatus.CREATED;
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        orderService.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
    
}
