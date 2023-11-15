package com.example.demo.services;

import com.example.demo.handlers.OrderHandler;
import com.example.demo.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    @Autowired
    private List<OrderHandler> orderHandlers;
    public void processOrder(Order o){
        for (OrderHandler oh : orderHandlers){
            boolean res = oh.handle(o);
            if (!res) {
                System.out.println("Не все проверки пройдены!");
                return;
                /* логика обработки непройденного заказа */
            }
        }
        System.out.println("Заказ №" + o.id() + " прошел все проверки.");
        /* Все проверки пройдены. */
    }
}
