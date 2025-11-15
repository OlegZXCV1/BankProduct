package com.bank.products.cards;

/**
 * Represents a currency debit card product.
 * It extends the base DebitCard class.
 */
public class CurrencyDebitCard extends DebitCard {

  /**
   * Constructs a new CurrencyDebitCard.
   *
   * @param name     the name of the card
   * @param currency the currency of the card
   * @param balance  the initial balance of the card
   */
  public CurrencyDebitCard(String name, String currency, double balance) {
    super(name, currency, balance);
  }
}
