package org.example.mySubTask;

import java.util.ArrayList;
import java.util.List;

public class ShopFacade {
    private List<Product> productShelf;
    private ShoppingCart cart;
    public Order.OrderBuilder orderBuilder;
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
    public void addToCart(Product product){
        cart.addProduct(product);
        System.out.println("Товар добавлен в корзину.");
//        orderBuilder.setCart(cart);
    }
    public Order returnCurrentOrder(){
        return orderBuilder.build();
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
