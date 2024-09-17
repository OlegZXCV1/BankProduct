package org.example.card;

import org.example.BankProduct;

import java.math.BigDecimal;

public class DebitCard extends BankProduct implements Card {

    public DebitCard(String currency, BigDecimal balance, String name) {
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

    @Override
    public void withdraw(BigDecimal amount) {
        if (balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
        } else
            throw new IllegalArgumentException("Insufficient balance");

    }
}
