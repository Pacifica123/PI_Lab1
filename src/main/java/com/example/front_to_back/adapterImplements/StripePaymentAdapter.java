package com.example.front_to_back.adapterImplements;

import com.example.front_to_back.tools.PaymentAdapter;
import com.stripe.model.checkout.Session;

import com.stripe.param.checkout.SessionCreateParams;

public class StripePaymentAdapter implements PaymentAdapter {
    private final String stripeSecretKey = "sk_test_51O2CA5HyjWPuYVM61i1zpWEanVvjS4KFIlzITsspk83399CP8U1tjgFQfsTQrEsBzY4XVpIZ82fzSLfo4h7RvQFC00uIvgWrYn";
    private String url;
    private String successfulUrl;
    private String cancelUrl;
    @Override
    public Session createPaymentSession(String successFilePath, String cancelFilePath, String filePath, String priceId){
        // Stripe API
        try {
            // Создаем параметры для сессии платежа
            // (вроде разобрались..): разобраться с хостингом
            SessionCreateParams params = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl("http://localhost:8080/payments/ok")  // Замените на свой URL успешной оплаты
                    .setCancelUrl("http://localhost:8080/payments/cancel")    // Замените на свой URL отмены оплаты
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setQuantity(1L)
                                    .setPrice(priceId)
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
