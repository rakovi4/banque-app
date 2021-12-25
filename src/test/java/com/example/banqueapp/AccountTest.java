package com.example.banqueapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AccountTest {

    @Test
    public void whenWeGetBalance_ShouldReturnZero(){
        AccountService service = new AccountService();
        int balance = service.getBalance();
        assertEquals(0, balance);
    }

    @Test
    public void whenWeAdd100AndGetBalance_ShouldReturn100(){
        AccountService service = new AccountService();
        service.addMoney(100);
        assertEquals(100, service.getBalance());
    }

    @Test
    public void whenWeAdd1000AndGetBalance_ShouldReturn1000(){
        AccountService service = new AccountService();
        service.addMoney(1000);
        assertEquals(1000, service.getBalance());
    }

    @Test
    public void whenAdd200Withdraw100_ShouldReturn100(){
        AccountService service = new AccountService();
        service.addMoney(200);
        service.withdrawMoney(100);
        assertEquals(100,service.getBalance());
    }

    @Test
    public void whenAdd200Withdraw300_ShouldThrowException(){
        AccountService service = new AccountService();
        service.addMoney(200);
        assertThrows(NotEnoughMoneyException.class, () -> service.withdrawMoney(300));
    }

    @Test
    public void whenAdd200Withdraw300_ShouldReturn200(){
        AccountService service = new AccountService();
        service.addMoney(200);
        try {
            service.withdrawMoney(300);
        } catch (Exception ignored) {}
        assertEquals(200,service.getBalance());
    }
}
