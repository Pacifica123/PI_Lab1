package org.example.mySubTask;

import java.io.UnsupportedEncodingException;

public class YooMoneyAdapter implements Payment {
    private YooMoneyApi yooMoneyApi;

    public YooMoneyAdapter(YooMoneyApi yooMoneyApi) {
        this.yooMoneyApi = yooMoneyApi;
    }
    public void processPayment(String wallet_id, double amount, String recipient, String message) throws UnsupportedEncodingException {
        yooMoneyApi.pay(wallet_id, amount, recipient, message);
    }
}
