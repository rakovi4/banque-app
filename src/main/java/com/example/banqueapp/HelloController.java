package com.example.banqueapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpClient;

@RestController
public class HelloController {

    @Autowired
    AccountService service;

    @GetMapping(path = "/hello")
    public String hello(){
        return "hello Marina!\n";
    }

    @GetMapping(path = "/customers/{customerId}/balance")
    public String balance(@PathVariable(name = "customerId") String customerId){
        return String.valueOf(service.getBalance(customerId));
    }

    @GetMapping(path = "/customers/{customerId}/add")
    public void add(@PathVariable(name = "customerId") String customerId,
                    @RequestParam(name = "money") String money){
        service.addMoney(Integer.parseInt(money), customerId);
    }

    @GetMapping(path = "/customers/{customerId}/withdraw")
    public void withdraw(@PathVariable(name = "customerId") String customerId,
                    @RequestParam(name = "money") String money){
        service.withdrawMoney(Integer.parseInt(money), customerId);
    }

}
