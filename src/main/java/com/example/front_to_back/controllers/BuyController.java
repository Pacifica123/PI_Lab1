package com.example.front_to_back.controllers;

import com.example.front_to_back.tools.ShopFacade;
import com.example.front_to_back.models.Product;
import com.sun.net.httpserver.Authenticator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/payments")
public class BuyController {
    private ShopFacade facade;

    @GetMapping("/pre-create_session")
    public String BuyIt(@RequestBody Product pr, @RequestParam("payment_type") String paymentAdapter){
        facade = new ShopFacade(pr, paymentAdapter); // за сессию покупаем 1 товар
        return "redirect:"+facade.getPaymentSessionUrl(); // либо facade.getSessionFilePath() напрямую..
    }

    @PostMapping("/ok")
    public String SuccessPage(){
        String url = facade.getSuccessUrl();
        facade.calculateDiscount();
        facade.createOrder();
        facade.BUY();
        // Возвращаем URL для перенаправления на страницу оплаты
        return "redirect:"+url;
    }

    @GetMapping("/cancel")
    public String CancelPage(){
        String url = facade.getCancelUrl();
        return "redirect:"+url;
    }
}
