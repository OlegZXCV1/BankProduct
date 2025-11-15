package com.bank.products.cards;

/**
 * Represents a credit card product.
 * It extends the base Card class and adds credit-specific functionality.
 */
public class CreditCard extends Card {

  private double interestRate;
  private double debt;

  /**
   * Constructs a new CreditCard.
   *
   * @param name         the name of the card
   * @param currency     the currency of the card
   * @param balance      the initial balance of the card
   * @param interestRate the interest rate of the card
   */
  public CreditCard(String name, String currency, double balance, double interestRate) {
    super(name, currency, balance);
    this.interestRate = interestRate;
    this.debt = 0;
  }

  @Override
  public void withdraw(double amount) {
    debt += amount;
  }

  /**
   * Returns the debt of the card.
   *
   * @return the debt of the card
   */
  public double getDebt() {
    return debt;
  }

  /**
   * Returns the interest rate of the card.
   *
   * @return the interest rate of the card
   */
  public double getInterestRate() {
    return interestRate;
  }
}
