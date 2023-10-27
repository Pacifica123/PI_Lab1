package com.example.front_to_back.adapterImplements;

import com.example.front_to_back.PaymentAdapter;
import com.example.front_to_back.models.Cart;
import com.stripe.model.checkout.Session;

import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

public class StripePaymentAdapter implements PaymentAdapter {
    private final String stripeSecretKey = "";
    private String url;
    @Override
    public Session createPaymentSession(Cart cart){
        // Stripe API
        try {
            // Создаем параметры для сессии платежа
            String YOUR_DOMAIN = "http://localhost:4242";
            String SUCCESS_URL = YOUR_DOMAIN+"/resources/public/success.html";
            String CANCEL_URL = YOUR_DOMAIN+"/resources/public/cancel.html";
            //TODO: разобраться со скидкой
            SessionCreateParams params = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl(SUCCESS_URL)  // Замените на свой URL успешной оплаты
                    .setCancelUrl(CANCEL_URL)    // Замените на свой URL отмены оплаты
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setQuantity(1L)
                                    .setPrice("price_1O2rKBHyjWPuYVM6GnH3p5nn")  // Замените на свой идентификатор цены
                                    .build())
                    .build();

            // Создаем сессию платежа с использованием параметров
            Session session = Session.create(params);

            url = session.getUrl(); // тут возможно надо session.getSuccessUrl();
            // Возвращаем сессию платежа
            return session;
        } catch (Exception e) {
            // Обработка ошибки, если что-то пошло не так
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public String getUrl() {
        return url;
    }
}
