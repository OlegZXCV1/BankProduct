package com.bank.products.deposits;

import com.bank.products.AbstractBankProduct;
import com.bank.products.behaviors.Closable;
import com.bank.products.behaviors.Depositable;

public class Deposit extends AbstractBankProduct implements Depositable, Closable {
    private boolean closed = false;

    public Deposit(String name, String currency, double balance) {
        super(name, currency, balance);
    }

    @Override
    public void deposit(double amount) {
        if (closed) {
            throw new IllegalStateException("Deposit is closed");
        }
        setBalance(getBalance() + amount);
    }

    @Override
    public void close() {
        this.closed = true;
    }

    public boolean isClosed() {
        return closed;
    }
}
