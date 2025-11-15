package com.bank.products;

import com.bank.products.behaviors.Depositable;
import com.bank.products.behaviors.Withdrawable;

/**
 * Represents a mortgage product.
 * It extends the AbstractBankProduct class and implements the Withdrawable and Depositable
 * interfaces.
 */
public class Mortgage extends AbstractBankProduct implements Withdrawable, Depositable {

  /**
   * Constructs a new Mortgage.
   *
   * @param name     the name of the mortgage
   * @param currency the currency of the mortgage
   * @param balance  the initial balance of the mortgage
   */
  public Mortgage(String name, String currency, double balance) {
    super(name, currency, balance);
  }

  @Override
  public void deposit(double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Deposit amount must be positive.");
    }
    this.balance -= amount;
  }

  @Override
  public void withdraw(double amount) {
    throw new UnsupportedOperationException("Withdrawal from a mortgage is not supported.");
  }
}
