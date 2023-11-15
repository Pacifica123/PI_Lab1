package com.example.demo.handlers;

import com.example.demo.models.Product;

public interface UserObserver {
    public void handleEvent(Product product, String actionMessage);
}
