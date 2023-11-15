package com.example.demo.MyDB;

import com.example.demo.models.Product;
import com.example.demo.models.User;

import java.util.HashMap;
import java.util.Map;

public class UserDB {
    private static final Map<Long, User> users = new HashMap<>();

    static {
        // Имитация заполнения базы данных товарами при старте приложения.
        users.put(1L, new User(
                1L,
                "Джон",
                "red@gmail.com",
                "f7dg829or3eivyuf",
                User.Role.USER
        ));
        users.put(2L, new User(
                2L,
                "Алексей",
                "yellow@gmail.com",
                "f29or3eivyu7dg8f",
                User.Role.USER
        ));
        users.put(3L, new User(
                3L,
                "Александр",
                "green@gmail.com",
                "3eivyuf7dg829orf",
                User.Role.USER
        ));
    }

    public static User getUserById(Long id) {
        return users.get(id);
    }
    public static User getUserByEmail(String email){
        return users.values().stream()
                .filter(user -> user.Email().equals(email))
                .findFirst()
                .orElse(null);
    }
}
