package com.example.demo.adapters;

import com.stripe.model.checkout.Session;

import com.stripe.param.checkout.SessionCreateParams;

public class StripePaymentAdapter implements PaymentAdapter {
    private final String stripeSecretKey = "sk_test_51O2CA5HyjWPuYVM61i1zpWEanVvjS4KFIlzITsspk83399CP8U1tjgFQfsTQrEsBzY4XVpIZ82fzSLfo4h7RvQFC00uIvgWrYn";
    private String url;
    private String successfulUrl;
    private String cancelUrl;
    @Override
    public Session createPaymentSession(String successFilePath, String cancelFilePath, String filePath, String priceId){
        try {
            SessionCreateParams params = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl(successfulUrl)
                    .setCancelUrl(cancelUrl)
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setQuantity(1L)
                                    .setPrice(priceId)
                                    .build()
                    ).build();
            // Создаем сессию платежа:
            Session session = Session.create(params);
            session.setUrl(successFilePath);
            url = session.getUrl();
            successfulUrl = session.getSuccessUrl();
            cancelUrl = session.getCancelUrl();
            return session;
        }
        catch (Exception e){
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
