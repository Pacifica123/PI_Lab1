package com.example.front_to_back.tools;

import com.example.front_to_back.adapterImplements.StripePaymentAdapter;
import com.example.front_to_back.models.Order;
import com.example.front_to_back.models.OrderBuilder;
import com.example.front_to_back.models.Product;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.checkout.Session;

/**
 * ДАННЫЙ ФАСАД ОБЪЕДИНЯЕТ СЕРВИСЫ И АДАПТЕРЫ
 */
public class ShopFacade {
    // ------------------------------------
    // НАСТРОЙКИ ФАСАДА (было бы неплохо их вынести...)
    private Product product;
    private Object session;
    private Order orderRecord;
    private OrderBuilder builder;
    private PaymentAdapter payment;
    private String typePayService;
    private String successFilePath = "";
    private String cancelFilePath = "http://v900053p.beget.tech/cancel.html";
    private String sessionFilePath = "http://v900053p.beget.tech/index.html";
    // ------------------------------------
        public ShopFacade(String price_id, String adapter) throws StripeException {
//            Stripe.apiKey = "sk_test_51O2CA5HyjWPuYVM61i1zpWEanVvjS4KFIlzITsspk83399CP8U1tjgFQfsTQrEsBzY4XVpIZ82fzSLfo4h7RvQFC00uIvgWrYn";
            Stripe.apiKey = "sk_test_51O2CA5HyjWPuYVM61i1zpWEanVvjS4KFIlzITsspk83399CP8U1tjgFQfsTQrEsBzY4XVpIZ82fzSLfo4h7RvQFC00uIvgWrYn";
//            Stripe.apiKey = "pk_test_51O2CA5HyjWPuYVM6CbMo7MJmqflO1yJ9oZfDumkQF2JZanQT5PleqzVk5jlqqBj5bsEG0ILjA0gjTXPXYp9Xl4mO00a0kh6HuS";
            Object priceInfo = Price.retrieve(price_id);
            this.product = new Product(price_id, priceInfo);
            this.typePayService = adapter;
            this.payment = choosePaymentAdapter(adapter);
            this.builder = new OrderBuilder();
        }
    public void calculateDiscount() throws Exception{
        session = payment.createPaymentSession(sessionFilePath, successFilePath, cancelFilePath, this.product.id());
        switch (typePayService){
            case "stripe":
            {
                // КОСТЫЛЬ ВАШЕМУ ВНИМАНИЮ:
                Price p = (Price) product.info();
                builder.costBefore = p.getUnitAmount();
                Session stripe = (Session)session; // самый важный объект.
//                Product p = new Product(priceId, stripe.getLineItems().getData().get(0));
                Discount d = new Discount();
                d.setProduct(this.product);
                d.setDiscountPercent((long)10);
                builder.costAfter = d.returnFinalCost(this.product.info(), this.typePayService);
            }
        }

    }
    public void createOrder(String countryRegion, String nameOwner, String cardNum, String email){
        // TODO: создание заказа с указанием цены до и после скидки
        Price p = (Price) product.info();
        Session stripe = (Session)session;
        orderRecord = new Order(
                email,
                cardNum,
                nameOwner,
                typePayService,
                builder.costBefore,
                builder.costAfter
        );

    }
    public void BUY()  {
        // Логика добавления заказа в БД
        // Вывод заказа:
        System.out.println("Цена до: " + orderRecord.costBefore());
        System.out.println("Цена после: " + orderRecord.costAfter());
        System.out.println(orderRecord.email());
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
