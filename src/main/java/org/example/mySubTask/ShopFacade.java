package org.example.mySubTask;

import java.util.ArrayList;
import java.util.List;

public class ShopFacade {
    private List<Product> productShelf;
    private ShoppingCart cart;
    private Order.OrderBuilder orderBuilder;
    private Order currentOrder;
    private List<Order> orders;
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
    // TODO: че нужно для покупки?
    public boolean BUY(){
        boolean succesfull = false;
        // логика покупки
        // ...
        // заносим в БД
        returnCurrentOrder().placeAnOrder();
        return succesfull;
    }
}
