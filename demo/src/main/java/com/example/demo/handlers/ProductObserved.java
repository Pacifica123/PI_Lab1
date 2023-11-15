package com.example.demo.handlers;

import com.example.demo.models.Product;

import java.util.ArrayList;
import java.util.List;

public interface ProductObserved {

    public void addObserver(UserObserver observer);
    public void removeObserver(UserObserver observer);

    public void notifyObserver(Product product, String actionMessage);
}
