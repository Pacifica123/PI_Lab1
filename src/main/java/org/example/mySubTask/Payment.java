package org.example.mySubTask;

import java.io.UnsupportedEncodingException;

public interface Payment {
    void processPayment(String wallet_id, double amount, String recipient, String message) throws UnsupportedEncodingException;
}
