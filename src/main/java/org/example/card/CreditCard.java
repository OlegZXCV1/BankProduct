package org.example.card;

import org.example.BankProduct;

import java.math.BigDecimal;

public class CreditCard extends BankProduct implements Card {
    private double interestRate;
    private BigDecimal debt;

    public CreditCard(String currency, BigDecimal balance, String name, double interestRate) {
        super(currency, balance, name);
        this.interestRate = interestRate;
        this.debt = BigDecimal.valueOf(0);

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
        debt = debt.add(amount);
    }

    public BigDecimal getDebt() {
        return debt.multiply(BigDecimal.valueOf(1 + (interestRate / 100)));
    }
}
