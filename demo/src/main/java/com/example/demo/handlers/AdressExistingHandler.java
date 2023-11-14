package com.example.demo.handlers;

import com.example.demo.MyDB.AdressDB;
import com.example.demo.MyDB.ProductDB;
import com.example.demo.models.Order;
import com.example.demo.models.Product;
import org.springframework.stereotype.Component;

@Component
@org.springframework.core.annotation.Order(3)
public class AdressExistingHandler implements OrderHandler {
    @Override
    public boolean handle(Order order) {
        // Логика проверки существования в БД указанного адреса доставки.
        String adress = order.address();
        Long a = AdressDB.getIdByAdress(adress);
        return a != null; // адресс доставки существует в БД или нет?
    }
}
