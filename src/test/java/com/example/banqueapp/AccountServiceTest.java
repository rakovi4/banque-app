package com.example.banqueapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AccountServiceTest {

    public static final String CUSTOMER_ID1 = "8b081381-cabc-4471-9e00-f239cfbb7f3d";
    public static final String CUSTOMER_ID2 = "8b081381-cabc-4471-9e00-f239cfbb7f4d";

    @Test
    public void whenWeGetBalance_ShouldReturnZero(){
        AccountService service = new AccountService();
        int balance = service.getBalance(CUSTOMER_ID1);
        assertEquals(0, balance);
    }

    @Test
    public void whenWeAdd100AndGetBalance_ShouldReturn100(){
        AccountService service = new AccountService();
        service.addMoney(100, CUSTOMER_ID1);
        assertEquals(100, service.getBalance(CUSTOMER_ID1));
    }

    @Test
    public void whenWeAdd1000AndGetBalance_ShouldReturn1000(){
        AccountService service = new AccountService();
        service.addMoney(1000, CUSTOMER_ID1);
        assertEquals(1000, service.getBalance(CUSTOMER_ID1));
    }

    @Test
    public void whenAdd200Withdraw100_ShouldReturn100(){
        AccountService service = new AccountService();
        service.addMoney(200, CUSTOMER_ID1);
        service.withdrawMoney(100, CUSTOMER_ID1);
        assertEquals(100,service.getBalance(CUSTOMER_ID1));
    }

    @Test
    public void whenAdd100Withdraw99_ShouldReturn100(){
        AccountService service = new AccountService();
        service.addMoney(100, CUSTOMER_ID1);
        service.withdrawMoney(99, CUSTOMER_ID1);
        assertEquals(1,service.getBalance(CUSTOMER_ID1));
    }

    @Test
    public void whenAdd200Withdraw300_ShouldThrowException(){
        AccountService service = new AccountService();
        service.addMoney(200, CUSTOMER_ID1);
        assertThrows(NotEnoughMoneyException.class, () -> service.withdrawMoney(300, CUSTOMER_ID1));
    }

    @Test
    public void whenAdd200Withdraw300_ShouldReturn200(){
        AccountService service = new AccountService();
        service.addMoney(200, CUSTOMER_ID1);
        try {
            service.withdrawMoney(300, CUSTOMER_ID1);
        } catch (Exception ignored) {}
        assertEquals(200,service.getBalance(CUSTOMER_ID1));
    }

    @Test
    public void whenAdd200ToCustomer1_ShouldReturn0ForCustomer2(){
        AccountService service = new AccountService();
        service.addMoney(200, CUSTOMER_ID1);
        assertEquals(0, service.getBalance(CUSTOMER_ID2));
    }

    @Test
    public void whenBalance0Withdraw300_ShouldThrowException() {
        AccountService service = new AccountService();
        assertThrows(NotEnoughMoneyException.class, () -> service.withdrawMoney(300, CUSTOMER_ID1));
    }
}
