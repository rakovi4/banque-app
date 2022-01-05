package com.example.banqueapp;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AccountService {
    private int balance = 0;
    private HashMap balanceMap = new HashMap();

    public int getBalance(String customerId) {
        balanceMap.get(customerId);
        return (int) balanceMap.get(customerId);
    }

    public void addMoney(int money, String customerId) {
        balance += money;
    }

    public void withdrawMoney(int money) {
        if (balance < money){
            throw new NotEnoughMoneyException();
        }
        balance = balance - money;
    }



}
