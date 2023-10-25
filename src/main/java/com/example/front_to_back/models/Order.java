package com.example.front_to_back.models;

public record Order(
        Long id,
        String email,
        String cardNum,
        String nameOwner
) { }
