package com.bank.products;

/**
 * Interface for all bank products.
 * It defines the common methods that all bank products must implement.
 */
public interface BankProduct {

  /**
   * Returns the name of the product.
   *
   * @return the name of the product
   */
  String getName();

  /**
   * Returns the currency of the product.
   *
   * @return the currency of the product
   */
  String getCurrency();

  /**
   * Returns the balance of the product.
   *
   * @return the balance of the product
   */
  double getBalance();
}
