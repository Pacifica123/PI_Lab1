package com.example.front_to_back.tools;

import com.example.front_to_back.adapterImplements.StripePaymentAdapter;
import com.example.front_to_back.models.Product;
import com.example.front_to_back.tools.PaymentAdapter;
import com.stripe.model.checkout.Session;

/**
 * ДАННЫЙ ФАСАД ОБЪЕДИНЯЕТ СЕРВИСЫ И АДАПТЕРЫ
 */
public class ShopFacade {
    // ------------------------------------
    // НАСТРОЙКИ ФАСАДА (было бы неплохо их вынести...)
    private Product product;
    private PaymentAdapter payment;
    private String typePayService;
    private String successFilePath = "";
    private String cancelFilePath = "";
    private String sessionFilePath = "";
    // ------------------------------------
    public ShopFacade(Product product, String adapter){
        this.product = product;
        this.typePayService = adapter;
        this.payment = choosePaymentAdapter(adapter);
    }
    public void calculateDiscount(){
        //  TODO: как-то выцепить цену и другие свойства и перенести их в Product...
    }
    public void createOrder(){
        // TODO: создание заказа с указанием цены до и после скидки

    }
    public void BUY(){
        Object session = payment.createPaymentSession(sessionFilePath, successFilePath, cancelFilePath);
        switch (typePayService){
            case "stripe":
            {
                // КОСТЫЛЬ ВАШЕМУ ВНИМАНИЮ:
                Session stripe = (Session)session; // самый важный объект.
                Product p = new Product("", stripe.getLineItems().getData().get(0));
                Discount d = new Discount();
                d.setProduct(p);
                d.setDiscountPercent((long)10);
                stripe.setAmountTotal(d.returnFinalCost());
            }
        }
        // TODO далее отправить post запрос о покупке (что-то типа checkout) на payment как-то
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

    public String getSuccessUrl(){
        return payment.getSuccessUrl();
    }
    public String getCancelUrl(){
        return payment.getCancelUrl();
    }

    public String getSessionFilePath() {
        return sessionFilePath;
    }
}
