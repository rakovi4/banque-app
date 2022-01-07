package com.example.banqueapp;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AccountService {
    private final HashMap<String, Integer> balanceMap = new HashMap<>();

    public int getBalance(String customerId) {
        return getBalanceFromMap(customerId);
    }

    public void addMoney(int money, String customerId) {
        Integer balance = getBalanceFromMap(customerId);
        balanceMap.put(customerId, balance + money);
    }

    public void withdrawMoney(int money, String customerId) {
        int balance = getBalanceFromMap(customerId);
        if (balance < money){
            throw new NotEnoughMoneyException();
        }
        balanceMap.put(customerId, balance - money);
    }

    private Integer getBalanceFromMap(String customerId) {
        Integer balance = balanceMap.get(customerId);
        if (balance == null) {
            return 0;
        }
        return balance;
    }


}
