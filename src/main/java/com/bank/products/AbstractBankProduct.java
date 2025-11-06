package com.bank.products;

public abstract class AbstractBankProduct implements BankProduct {
    protected String name;
    protected String currency;
    protected double balance;

    public AbstractBankProduct(String name, String currency, double balance) {
        this.name = name;
        this.currency = currency;
        this.balance = balance;
    }

    @Override
    public String getName() { return name; }

    @Override
    public String getCurrency() { return currency; }

    @Override
    public double getBalance() { return balance; }

    protected void setBalance(double newBalance) {
        this.balance = newBalance;
    }
}
