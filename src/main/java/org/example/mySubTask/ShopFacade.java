package org.example.mySubTask;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ShopFacade {
    private List<Product> productShelf;
    private String AUTHORIZATION_TOKEN = "Bearer 410012345678901.0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123";
    private ShoppingCart cart;
    private Order.OrderBuilder orderBuilder;
    private Order currentOrder;
    private String SHOPKEEPERS_WALLET = "41001101140";
    private List<Order> old_orders;
    private String Host_wallet; // кошелек (например yoomoney.ru)
    public ShopFacade(){
        productShelf = new ArrayList<Product>();
        orderBuilder = new Order.OrderBuilder();
        orderBuilder.setCart(cart);
    }
    public void displayProductsOnConsole() {
        System.out.println("Список товаров:");
        for (Product product : productShelf) {
            System.out.println(product.getName() + " - " + product.getPrice());
        }
    }
    public List<Product> returnProducts(){
        return productShelf;
    }

    /**
     * добавить в корзину
     * @param product
     */
    public void addToCart(Product product){
        cart.addProduct(product);
        System.out.println("Товар добавлен в корзину.");
//        orderBuilder.setCart(cart);
    }
    public Order returnCurrentOrder(){

        return currentOrder;
    }

    /**
     * бонусная карта
     */
    public void onCard(){
        orderBuilder.setCard(true);
    }

    /**
     * оформить заказ
     */
    public void createOrder(){
        currentOrder = orderBuilder.build();
    }
    public void displayCurrentOrder(){
        System.out.println("СПИСОК ПРОДУКТОВ:");
        for (Product p: cart.getProducts()
             ) {
            System.out.println(p.getName() + " - " + p.getPrice() + " | " + p.getId());
        }
        System.out.println("Бонусная карта: " + currentOrder.isCard);
        System.out.println("Цена без скидки: " + currentOrder.getTotalCost());
        System.out.println("Цена со скидкой: " + currentOrder.getTotalCostWithDiscount());
    }

    /** TODO: вот что нужно:
     * pattern_id - ID шаблона платежа (соответствует номеру витрины scid магазина)
     * to - ID получателя перевода (№счета, №телефона, email)
     * amount - сумма к оплате
     * comment и message не обязательные
     *
     *
     * Еще нужен Host - источник кошелька
     *
     * Еще нужен токен (как вариант: )
     *
     * Content-Type: application/x-www-form-urlencoded
     * Content-Length: 234
     * @return
     */
    public String BUY(String wallet_scid , Payment payMethod) throws UnsupportedEncodingException {
        String comment = "";
        String message = "";
        String json = "";
//        boolean succesfull = false;


        /** логика покупки
         * 1. Посылаем POST запрос на АПИ
         * 2. Получаем ответ JSON
         * 3. Возвращаем его
         */
        payMethod.processPayment(wallet_scid, currentOrder.getTotalCostWithDiscount(), SHOPKEEPERS_WALLET, message);

        // заносим в БД
        returnCurrentOrder().placeAnOrder();
        old_orders.add(returnCurrentOrder());
        orderBuilder.resetConfig();

        return json;
    }
}
