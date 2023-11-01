package com.example.front_to_back.adapterImplements;

import com.example.front_to_back.tools.PaymentAdapter;
import com.stripe.model.checkout.Session;

import com.stripe.param.checkout.SessionCreateParams;

public class StripePaymentAdapter implements PaymentAdapter {
    private final String stripeSecretKey = "";
    private String url;
    private String successfulUrl;
    private String cancelUrl;
    @Override
    public Session createPaymentSession(String successFilePath, String cancelFilePath, String filePath){
        // Stripe API
        try {
            // Создаем параметры для сессии платежа
            // (вроде разобрались..): разобраться с хостингом
            SessionCreateParams params = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl("successFilePath")  // Замените на свой URL успешной оплаты
                    .setCancelUrl("cancelFilePath")    // Замените на свой URL отмены оплаты
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setQuantity(1L)
                                    .setPrice("price_1O2rKBHyjWPuYVM6GnH3p5nn")  // Замените на свой идентификатор цены
                                    .build())
                    .build();

            // Создаем сессию платежа с использованием параметров
            Session session = Session.create(params);
            session.setUrl(successFilePath);

            url = session.getUrl();
            successfulUrl = session.getSuccessUrl();
            cancelUrl = session.getCancelUrl();
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
    @Override
    public String getSuccessUrl(){
        return successfulUrl;
    }
    @Override
    public String getCancelUrl(){
        return cancelUrl;
    }
}
