package com.example.demo.MyDB;

import com.example.demo.models.Product;

import java.util.HashMap;
import java.util.Map;

public class AdressDB {
    private static final Map<String, Long > adressRecords = new HashMap<>();

    static {
        // Имитация заполнения базы данных товарами при старте приложения.
        adressRecords.put("адресс с кодом 1", 2L);
        adressRecords.put("адресс с кодом 2", 2L);
    }

    public static Long getIdByAdress(String adress) {
        return adressRecords.get(adress);
    }
}
