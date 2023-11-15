package com.example.demo.handlers;

import com.example.demo.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductNotifer implements ProductObserved {
    List<UserObserver> subers = new ArrayList<>();
    Product product;
    public ProductNotifer(Product thisProduct){
        product = thisProduct;
    }

    @Override
    public void addObserver(UserObserver o){
        subers.add(o);
        System.out.println("Был подписан пользователь на уведомления");
    }
    @Override
    public void removeObserver(UserObserver o){
        subers.remove(o);
        System.out.println("Был отписан пользователь на уведомления");
    }

    @Override
    public void notifyObserver(Product product, String actionMessage) {

            for(UserObserver o : subers){
                o.handleEvent(product, actionMessage);
            }

    }
}
