package com.example.front_to_back.controllers;

import com.example.front_to_back.tools.ShopFacade;
import com.example.front_to_back.models.Product;
import com.stripe.exception.StripeException;
import com.sun.net.httpserver.Authenticator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
//@Controller
@RequestMapping(value = "/payments")
public class BuyController {
    private ShopFacade facade;

    @RequestMapping("/")
    //
    public ModelAndView BuyIt(){

        ModelAndView mav = new ModelAndView();
        mav.setViewName("index.html");
//        return "redirect:index.html";
        return mav;
    }

//    @PostMapping("/ok")
    @RequestMapping("/ok")
    public ModelAndView Pay(@RequestParam("price_id") String priceId,
                                    @RequestParam("payment_type") String paymentAdapter,
                                    @RequestParam("countryRegion") String countryRegion,
                                    @RequestParam("nameOwner") String nameOwner,
                                    @RequestParam("cvvCvc") String cvvCvc,
                                    @RequestParam("mmyy") String mmyy,
                                    @RequestParam("cardNum") String cardNum,
                                    @RequestParam("email") String email
    ) throws Exception {
        facade = new ShopFacade(priceId, paymentAdapter); // за сессию покупаем 1 товар
        String url = facade.getSuccessUrl();
        facade.calculateDiscount();
        facade.createOrder();
        facade.BUY();
        ModelAndView mav = new ModelAndView();
        mav.setViewName("success.html");
        return mav;
    }

//    @RequestMapping("/success")
//    public ModelAndView SuccessPage(){
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("success.html");
//        return mav;
//    }

    @GetMapping("/cancel")
    public String CancelPage(){
        String url = facade.getCancelUrl();
//        return "redirect:"+url;
        return "ОЧЕНЬ ЖАЛЬ";
    }
}
