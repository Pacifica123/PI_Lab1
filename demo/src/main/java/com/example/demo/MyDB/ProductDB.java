package com.example.demo.MyDB;

import com.example.demo.handlers.ProductNotifer;
import com.example.demo.handlers.ProductObserved;
import com.example.demo.models.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductDB {
    private static final Map<String, Product> productDatabase = new HashMap<>();

    static {
        // Имитация заполнения базы данных товарами при старте приложения.
        productDatabase.put("productId1", new Product("productId1", "Product 1", 10));
        productDatabase.put("productId2", new Product("productId2", "Product 2", 5));
    }

    public static Product getProductById(String productId) {
        return productDatabase.get(productId);
    }
    public static void updateProduct(String productId, Product p, ProductNotifer notifer){
        Product existCurrent = productDatabase.get(productId);
        if (existCurrent != null){
            productDatabase.put(productId, p);
            notifer.notifyObserver(p, "тут можно смотреть разницу и кидать что конкретно изменилось");
        }
    }
}
