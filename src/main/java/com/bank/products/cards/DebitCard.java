package com.bank.products.cards;

/**
 * Represents a debit card product.
 * It extends the base Card class.
 */
public class DebitCard extends Card {

  /**
   * Constructs a new DebitCard.
   *
   * @param name     the name of the card
   * @param currency the currency of the card
   * @param balance  the initial balance of the card
   */
  public DebitCard(String name, String currency, double balance) {
    super(name, currency, balance);
  }
}
