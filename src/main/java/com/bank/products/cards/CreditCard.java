package com.bank.products.cards;

public class CreditCard extends Card {
    private double interestRate;
    private double debt;

    public CreditCard(String name, String currency, double balance, double interestRate) {
        super(name, currency, balance);
        this.interestRate = interestRate;
        this.debt = 0;
    }

    @Override
    public void withdraw(double amount) {
        debt += amount;
    }

    public double getDebt() {
        return debt;
    }

    public double getInterestRate() {
        return interestRate;
    }
}
