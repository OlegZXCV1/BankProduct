package com.bank.products.cards;

import com.bank.products.AbstractBankProduct;
import com.bank.products.behaviors.Depositable;
import com.bank.products.behaviors.Withdrawable;

/**
 * Abstract base class for all card products.
 * It provides the basic functionality for depositing and withdrawing money.
 */
public abstract class Card extends AbstractBankProduct implements Depositable, Withdrawable {

  /**
   * Constructs a new Card.
   *
   * @param name     the name of the card
   * @param currency the currency of the card
   * @param balance  the initial balance of the card
   */
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
