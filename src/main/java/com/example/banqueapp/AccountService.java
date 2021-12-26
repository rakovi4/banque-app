package com.example.banqueapp;

import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private int balance = 0;
    public int getBalance() {
        return balance;
    }

    public void addMoney(int money) {
        balance += money;
    }

    public void withdrawMoney(int money) {
        if (balance < money){
            throw new NotEnoughMoneyException();
        }
        balance = balance - money;
    }


}
