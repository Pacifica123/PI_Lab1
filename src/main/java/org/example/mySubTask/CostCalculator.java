package org.example.mySubTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CostCalculator {
    public static Double GLOBAL_DISCOUNT_FOR_PAY = 0.15; //15% скидка по карте
    public static Double calcTotal(ShoppingCart cart){
        double totalCost = 0.0;
        for (Map.Entry<Long, Integer> p : cart.getCart().entrySet()) {
            Long id = p.getKey();
            totalCost += cart.getProduct(id).getPrice();
        }

        return totalCost;
    }
//    static List<Product.ProductCategory> categoryWithDiscount;
    static HashMap<Product.ProductCategory, Double> discountForCategory;

//    public void setCategoryWithDiscount(List<Product.ProductCategory> categoryWithDiscount) {
//        CostCalculator.categoryWithDiscount = categoryWithDiscount;
//    }

    public static void setDiscountForCategory(HashMap<Product.ProductCategory, Double> discountForCategory) {
        CostCalculator.discountForCategory = discountForCategory;
    }

    public static Double calcWithDiscount(ShoppingCart cart, boolean isCard){

        // : расчет специфической скидки для конкретных продуктов
        for (Product p: cart.getProducts()
        ) {
            p.setPrice(p.getPrice() - p.getPrice()*p.getSpecificDiscount());
        }

        // : расчет скидки по категориям товара
        if(discountForCategory != null){
            for (Product p: cart.getProducts()
                 ) {
                // Если категория продукта есть в списке скидок
                // и при этом нет специфической скидки
                // то считаем скидку по категории:
                if (discountForCategory.containsKey(p.getCategory())
                        && p.getSpecificDiscount() == 0.0){
                    p.setPrice(p.getPrice() - p.getPrice()*discountForCategory.get(p.getCategory()));
                }
            }
        }

        if (isCard) {
            return (calcTotal(cart) - calcTotal(cart)*GLOBAL_DISCOUNT_FOR_PAY);
        }

        return calcTotal(cart);
    }
}
