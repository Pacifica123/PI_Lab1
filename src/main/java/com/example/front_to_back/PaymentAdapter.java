package com.example.front_to_back;

import com.example.front_to_back.models.Cart;

public interface PaymentAdapter {
    Object createPaymentSession(Cart cart);
    String getUrl();
}
