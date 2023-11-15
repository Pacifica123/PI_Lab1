package com.example.demo.handlers;

import com.example.demo.models.Product;
import com.example.demo.models.User;

public class UserSuber implements UserObserver{
    User who;
    public UserSuber(User u){
        who = u;
    }
    @Override
    public void handleEvent(Product p, String actionMessage){
        System.out.println("Пользователь " + who.Name() + " получил уведомление о продукте " + p.getProductId());
        System.out.println("Сообщение об изменении: " + actionMessage);
        System.out.println("--------------------------------------------------------------------------------");
    }
}
