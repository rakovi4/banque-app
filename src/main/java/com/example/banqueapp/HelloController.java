package com.example.banqueapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    AccountService service;

    @GetMapping(path = "/hello")
    public String hello(){
        return "hello Marina!\n";
    }
    @GetMapping(path = "/balance")
    public String balance(){
        return String.valueOf(service.getBalance());
    }

    @GetMapping(path = "/add")
    public void add(@RequestParam(name = "money") String money){
        service.addMoney(Integer.parseInt(money));
    }

}
