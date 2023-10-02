package org.example.mySubTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShoppingCart {
    //TODO
    private List<Product> products;
    private HashMap<Long, Integer> productsTable;
    public HashMap<Long, Integer> getCart(){
        return productsTable;
    }
    public List<Product> getProducts(){
        return products;
    }
    public ShoppingCart(){
        this.products = new ArrayList<Product>();
    }
    public void addProduct(Product product){
        this.products.add(product);
    }
    public void removeThisProduct(Product product){
        this.products.remove(product);
    }
    public void clearCart(){
        this.products.clear();
    }
    public void removeNumbProduct(Integer orderNumb){
        this.products.remove(this.products.get(orderNumb));
    }

    public void removeProductById(final Long id){
        //TODO
        //this.products.removeIf(p -> p.getId() == id);
    }
    public Product getProduct(Long id){
        for (Product p: products) {
            if (p.getId() == null ? id == null : p.getId().equals(id)) return p;
        }
        return null;
    }
}
