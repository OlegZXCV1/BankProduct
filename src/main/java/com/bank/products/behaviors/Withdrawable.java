package com.bank.products.behaviors;

/**
 * Interface for products that can be withdrawn from.
 */
public interface Withdrawable {

  /**
   * Withdraws a given amount from the product.
   *
   * @param amount the amount to withdraw
   */
  void withdraw(double amount);
}
