package com.example.banqueapp;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AccountService {
    private int balance = 0;
    private final HashMap<String, Integer> balanceMap = new HashMap<>();

    public int getBalance(String customerId) {
        Integer balance = balanceMap.get(customerId);
        if (balance == null) {
            return 0;
        }

        return balance;
    }

    public void addMoney(int money, String customerId) {
        Integer balance = balanceMap.get(customerId);
        if (balance == null) {
            balance = 0;
        }
        balanceMap.put(customerId, balance + money);
    }

    public void withdrawMoney(int money, String customerId) {
        Integer balance = balanceMap.get(customerId);
        if (balance == null) {
            balance = 0;
        }
        if (balance < money){
            throw new NotEnoughMoneyException();
        }
        balanceMap.put(customerId, balance - money);
    }



}
