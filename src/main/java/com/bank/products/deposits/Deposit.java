package com.bank.products.deposits;

import com.bank.products.AbstractBankProduct;
import com.bank.products.behaviors.Closable;
import com.bank.products.behaviors.Depositable;

/**
 * Represents a deposit product.
 * It extends the AbstractBankProduct class and implements the Depositable and Closable interfaces.
 */
public class Deposit extends AbstractBankProduct implements Depositable, Closable {

  private boolean closed = false;

  /**
   * Constructs a new Deposit.
   *
   * @param name     the name of the deposit
   * @param currency the currency of the deposit
   * @param balance  the initial balance of the deposit
   */
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

  /**
   * Returns whether the deposit is closed.
   *
   * @return true if the deposit is closed, false otherwise
   */
  public boolean isClosed() {
    return closed;
  }
}
