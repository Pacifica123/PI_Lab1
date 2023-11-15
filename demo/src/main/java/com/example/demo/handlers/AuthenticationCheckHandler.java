package com.example.demo.handlers;

import com.example.demo.MyDB.UserDB;
import com.example.demo.models.Order;
import com.example.demo.models.User;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationCheckHandler implements OrderHandler {
    @Override
    @org.springframework.core.annotation.Order(1)
    public boolean handle(Order o) {
        // Логика проверки авторизации пользователя.
        User u = UserDB.getUserByEmail(o.email());
        if (u != null){
            return true;
        }
        return false; // Или false в случае ошибки.
    }
}
