package com.tutorial.microservices.order.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tutorial.microservices.order.data.Order;
import com.tutorial.microservices.order.data.OrderRepository;
import com.tutorial.microservices.order.service.exception.OrderNotFoundException;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService underTest;
    @Mock
    private OrderRepository repository;

    @Test
    public void getOrderByIdWhenIdNullShouldThrowNullPointerException() {
        // Given
        Long id = null;
        // when
        // Then
        assertThrows(NullPointerException.class, () -> underTest.getById(id));
    }
    
    @Test
    public void getOrderByIdWhenIdValidShouldReturTheOrder() {
        // Given
        Long id = 1L;
        Order expected = new Order(1L,null,null,0,null);
        // when
        when(repository.findById(id)).thenReturn(Optional.of(expected));
        Order order = underTest.getById(id);
        // Then
        assertEquals(expected, order);
    }
    
    @Test
    public void getOrderByIdWhenIdNotExistShouldThrowOrderNotFoundException() {
        // Given
        Long id = 1L;
        // when
        when(repository.findById(id)).thenReturn(Optional.empty());
        // Then
        assertThrows(OrderNotFoundException.class, () -> underTest.getById(id));
    }
    
    @Test
    public void deleteOrderByIdWhenIdNullShouldThrowNullPointerException() {
        // Given
        Long id = null;
        // when
        // Then
        assertThrows(NullPointerException.class, () -> underTest.deleteById(id));
    }
    
    @Test
    public void deleteOrderByIdWhenIdValidShouldDeleteTheOrder() {
        // Given
        Long id = 1L;
        // when
        when(repository.existsById(id)).thenReturn(true);
        underTest.deleteById(id);
        // Then
        verify(repository).deleteById(id);
    }
    
    @Test
    public void deleteOrderByIdWhenIdNotExistShouldThrowOrderNotFoundException() {
        // Given
        Long id = 1L;
        // when
        when(repository.existsById(id)).thenReturn(false);
        // Then
        assertThrows(OrderNotFoundException.class, () -> underTest.deleteById(id));
    }
    
    @Test
    public void saveOrderByIdWhenOrderNotNullShouldSaveTheOrder() {
        // Given
        Order order = new Order(1L,null,null,0,null);
        // when
        underTest.save(order);
        // Then
        verify(repository).save(order);
    }
    
    @Test
    public void saveOrderByIdWhenOrderIsNullShouldThrowNullPointerException() {
        // Given
        Order order = null;
        // when
        // Then
        assertThrows(NullPointerException.class, () -> underTest.save(order));
    }
    
    @Test
    public void saveAllOrderWhenEitherOrderAreNotNullShouldSaveTheOrders() {
        // Given
        Order order1 = new Order(1L,null,null,0,null);
        Order order2 = new Order(1L,null,null,0,null);
        List<Order> orders = Arrays.asList(order1, order2);
        // when
        underTest.saveAll(orders);
        // Then
        verify(repository).saveAll(orders);
    }
    
    @Test
    public void saveAllOrderWhenThereIsNullOrderInTheListShouldThrowNullPointerException() {
        // Given
        Order order = new Order(1L,null,null,0,null);
        List<Order> orders = Arrays.asList(order, null);
        // when
        // Then
        assertThrows(NullPointerException.class, () -> underTest.saveAll(orders));
    }

}
