package com.example.front_to_back.tools;

import com.example.front_to_back.models.Product;
import com.stripe.model.LineItem;

import java.util.List;

public class Discount {
    private Product p;
    private Long discountPercent;
    public void setProduct(Product p){
        this.p = p;
    }

    public void setDiscountPercent(Long discountPercent) {
        this.discountPercent = discountPercent;
    }
    public Long returnFinalCost(){
        LineItem s = (LineItem)(this.p.info());
        // КОСТЫЛЬ ВАШЕМУ ВНИМАНИЮ:
        return (s.getAmountTotal()*discountPercent/100);
    }
}
