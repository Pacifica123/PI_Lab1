package com.example.front_to_back;

import com.example.front_to_back.adapterImplements.StripePaymentAdapter;
import com.example.front_to_back.models.Cart;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * ДАННЫЙ ФАСАД ОБЪЕДИНЯЕТ СЕРВИСЫ И АДАПТЕРЫ
 */

public class ShopFacade {
    private Cart cart; // условная корзина пользователя
    private Object inventory; // условный инвертарь магазина (завязан на БД и в контексте Лабораторной работы роли не играет)
    private PaymentAdapter payment;
    public ShopFacade(Cart cart, String adapter){
        this.cart = cart;
        this.payment = choosePaymentAdapter(adapter);
    }
    public void BUY(){
        payment.createPaymentSession(cart);
        // далее отправить post запрос о покупке (что-то типа checkout) на payment как-то
    }
    public PaymentAdapter choosePaymentAdapter(String paymentMethod) {
        if ("stripe".equals(paymentMethod)) {
            return new StripePaymentAdapter();
        } else {
            return null;
        }
    }

    public String getPaymentSessionUrl() {
        return payment.getUrl();
    }
}
