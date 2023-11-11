package com.example.demo.adapters;

public interface PaymentAdapter {
    Object createPaymentSession(String successFilePath, String cancelFilePath, String filePath, String id);

    String getUrl();
    String getSuccessUrl();
    String getCancelUrl();
}
