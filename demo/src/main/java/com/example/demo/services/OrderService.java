package com.example.demo.services;

import com.example.demo.handlers.OrderHandler;
import com.example.demo.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private List<OrderHandler> orderHandlers;
    public void processOrder(Order o){
        for (OrderHandler oh : orderHandlers){
            boolean res = oh.handle(o);
            if (!res) {
                System.out.println("Не все проверки пройдены!");
                System.out.println("------------------------------");
                return;
                /* логика обработки непройденного заказа */
            }
        }
        System.out.println("Заказ №" + o.id() + " прошел все проверки.");
        /* Все проверки пройдены. */
    }
}
