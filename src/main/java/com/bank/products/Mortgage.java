package com.bank.products;

import com.bank.products.behaviors.Depositable;
import com.bank.products.behaviors.Withdrawable;

public class Mortgage extends AbstractBankProduct implements Withdrawable, Depositable {

    public Mortgage(String name, String currency, double balance) {
        super(name, currency, balance);
    }

    /**
     * @param amount
     */
    @Override
    public void deposit(double amount) {

    }

    /**
     * @param amount
     */
    @Override
    public void withdraw(double amount) {

    }
    // реализация логики
}
