package com.example.banqueapp;

public class AccountService {
    int balance = 0;
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
