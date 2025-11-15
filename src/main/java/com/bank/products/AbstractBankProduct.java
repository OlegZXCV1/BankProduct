package com.bank.products;

/**
 * Abstract base class for all bank products.
 * It provides the basic properties and methods that are common to all products.
 */
public abstract class AbstractBankProduct implements BankProduct {
  protected String name;
  protected String currency;
  protected double balance;

  /**
   * Constructs a new AbstractBankProduct.
   *
   * @param name     the name of the product
   * @param currency the currency of the product
   * @param balance  the initial balance of the product
   */
  public AbstractBankProduct(String name, String currency, double balance) {
    this.name = name;
    this.currency = currency;
    this.balance = balance;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getCurrency() {
    return currency;
  }

  @Override
  public double getBalance() {
    return balance;
  }

  protected void setBalance(double newBalance) {
    this.balance = newBalance;
  }
}
