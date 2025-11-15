package com.bank.service;

import com.bank.products.BankProduct;
import com.bank.products.behaviors.Depositable;
import com.bank.products.behaviors.Withdrawable;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * Service for managing bank products.
 */
@Service
public class BankProductService {

  private final Map<String, BankProduct> products = new HashMap<>();

  /**
   * Registers a new bank product.
   *
   * @param id      the id of the product
   * @param product the product to register
   */
  public void registerProduct(String id, BankProduct product) {
    products.put(id, product);
  }

  /**
   * Returns the product with the given id.
   *
   * @param id the id of the product
   * @return the product with the given id
   */
  public BankProduct getProduct(String id) {
    return products.get(id);
  }

  /**
   * Returns the balance of the product with the given id.
   *
   * @param id the id of the product
   * @return the balance of the product
   */
  public double getBalance(String id) {
    return products.get(id).getBalance();
  }

  /**
   * Deposits a given amount to the product with the given id.
   *
   * @param id     the id of the product
   * @param amount the amount to deposit
   */
  public void deposit(String id, double amount) {
    BankProduct product = products.get(id);
    if (product instanceof Depositable d) {
      d.deposit(amount);
    } else {
      throw new UnsupportedOperationException("This product cannot accept deposits");
    }
  }

  /**
   * Withdraws a given amount from the product with the given id.
   *
   * @param id     the id of the product
   * @param amount the amount to withdraw
   */
  public void withdraw(String id, double amount) {
    BankProduct product = products.get(id);
    if (product instanceof Withdrawable w) {
      w.withdraw(amount);
    } else {
      throw new UnsupportedOperationException("This product does not support withdrawals");
    }
  }
}
