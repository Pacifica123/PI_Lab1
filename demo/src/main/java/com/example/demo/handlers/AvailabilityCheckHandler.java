package com.example.demo.handlers;

import com.example.demo.models.Order;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityCheckHandler implements OrderHandler {
    @Override
    public boolean handle(Order o){
        // логика проверки на налличие товара на складе

        return false;
    }
}
