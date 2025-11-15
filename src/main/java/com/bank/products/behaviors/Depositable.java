package com.bank.products.behaviors;

/**
 * Interface for products that can be deposited to.
 */
public interface Depositable {

  /**
   * Deposits a given amount to the product.
   *
   * @param amount the amount to deposit
   */
  void deposit(double amount);
}
