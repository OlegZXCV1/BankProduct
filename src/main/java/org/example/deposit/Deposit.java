package org.example.deposit;

import org.example.BankProduct;

import java.math.BigDecimal;

public class Deposit extends BankProduct {

    public Deposit(String currency, BigDecimal balance, String name) {
        super(currency, balance, name);
    }


    @Override
    public void topUp(BigDecimal amount) {
        balance = balance.add(amount);
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    public void close() {
        balance = new BigDecimal(0);
        System.out.println("Deposit closed.");
    }
}
