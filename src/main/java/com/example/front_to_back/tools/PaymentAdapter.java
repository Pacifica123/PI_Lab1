package com.example.front_to_back.tools;


public interface PaymentAdapter {
    Object createPaymentSession(String successFilePath, String cancelFilePath, String filePath);
    String getUrl();
    String getSuccessUrl();
    String getCancelUrl();
}
