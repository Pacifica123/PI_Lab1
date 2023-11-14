package com.example.demo.handlers;

import com.example.demo.models.Order;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationCheckHandler implements OrderHandler {
    @Override
    @org.springframework.core.annotation.Order(1)
    public boolean handle(Order o) {
        // Логика проверки авторизации пользователя.
        return true; // Или false в случае ошибки.
    }
}
