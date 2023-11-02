package com.example.front_to_back.models;

public record Order(
        String email,
        String cardNum,
        String nameOwner,
        String paymentService,
        Long costBefore,
        Long costAfter
) { }
