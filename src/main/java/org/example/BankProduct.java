package org.example;

import java.math.BigDecimal;

public abstract class BankProduct {
    protected String currency;
    protected BigDecimal balance;
    protected String name;

    public BankProduct(String currency, BigDecimal balance, String name) {
        this.currency = currency;
        this.balance = balance;
        this.name = name;
    }

    public abstract void topUp(BigDecimal amount);
    public abstract BigDecimal getBalance();
}
