package com.example.demo.handlers;

import com.example.demo.models.Order;
import org.springframework.stereotype.Component;

@Component
public class AdressExistingHandler implements OrderHandler {
    @Override
    public boolean handle(Order order) {
        // Логика проверки существования в БД указанного адреса доставки.
        return true;
    }
}
