package com.example.front_to_back.controllers;

import com.example.front_to_back.ShopFacade;
import com.example.front_to_back.models.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.stripe.model.checkout.Session;


@RestController
@RequestMapping(value = "/payments")
public class BuyController {
    @Autowired
    private ShopFacade facade;

    @PostMapping("/create_session")
    public ResponseEntity<String> BuyIt(@RequestBody Cart cart, @RequestParam String paymentAdapter){
        facade = new ShopFacade(cart, paymentAdapter);
        facade.BUY();
        // Возвращаем URL для перенаправления на страницу оплаты
        String paymentSessionUrl = facade.getPaymentSessionUrl(); // Ваш метод в ShopFacade
        return ResponseEntity.ok(paymentSessionUrl);
    }
}
