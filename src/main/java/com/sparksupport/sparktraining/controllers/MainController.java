package com.sparksupport.sparktraining.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // (RestController = Controller + ResponseBody)
public class MainController {

    @GetMapping("/")
    public String home(){
        return "<h1>Hi Welcome to spring boot world</h1>";
    }
}
