package com.example.demo.handlers;

import com.example.demo.MyDB.ProductDB;
import com.example.demo.models.Order;
import com.example.demo.models.Product;
import org.springframework.stereotype.Component;

@Component
@org.springframework.core.annotation.Order(2)
public class AvailabilityCheckHandler implements OrderHandler {
    @Override
    public boolean handle(Order o){
        // логика проверки на налличие товара на складе
        String productId = o.productId();
        Product p = ProductDB.getProductById(productId);
        if (p != null && p.getQuantity() > 0){
            p.decreaseQuantity(1);
            return true; // товар есть и мы его взяли
        }
        else {
            System.out.println("Не пройдена проверка в AvailabilityCheckHandler");
            return false; // товар отсутствует
        }
    }
}
