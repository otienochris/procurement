package com.otienochris.procurement_management_system.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/cart")
public class CartController {

    @GetMapping("/index")
    public String index(){
        return "Hello world";
    }
}
