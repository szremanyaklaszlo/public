package com.training.sportsbetting.service.event;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.training.sportsbetting.service.ServiceTestCofig;

@SpringBootTest(classes = ServiceTestCofig.class)
public class SportEventCrudTest {
    
    
    @Test
    public void firstTest () {
        assertEquals("a", "a");
    }
    
}
