package com.example.front_to_back.tools;

import com.example.front_to_back.models.Product;
import com.stripe.model.Price;

public class Discount {
    private Product p;
    private Long discountPercent;
    public void setProduct(Product p){
        this.p = p;
    }

    public void setDiscountPercent(Long discountPercent) {
        this.discountPercent = discountPercent;
    }
    public long returnFinalCost(Object info, String typePaymentService) throws Exception {
//        LineItem s = (LineItem)(this.p.info());

        try {
            if(typePaymentService.contains("stripe")){
                Price price = (Price) info;
                // КОСТЫЛЬ ВАШЕМУ ВНИМАНИЮ:
                Long a = price.getUnitAmount() - (price.getUnitAmount() * discountPercent / 100);
                return a;
            }
        } catch (Exception e) {
            throw new Exception("nooo!!!");
        }
        return -1;
    }
}
