package com.bank.products.cards;

import com.bank.products.AbstractBankProduct;
import com.bank.products.behaviors.Depositable;
import com.bank.products.behaviors.Withdrawable;

public abstract class Card extends AbstractBankProduct implements Depositable, Withdrawable {
    public Card(String name, String currency, double balance) {
        super(name, currency, balance);
    }

    @Override
    public void deposit(double amount) {
        setBalance(getBalance() + amount);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > getBalance()) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        setBalance(getBalance() - amount);
    }
}
