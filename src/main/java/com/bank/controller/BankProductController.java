package com.bank.controller;

import com.bank.controller.dto.CreateCreditCardRequest;
import com.bank.controller.dto.CreateDebitCardRequest;
import com.bank.controller.dto.CreateDepositRequest;
import com.bank.controller.dto.ProductCreatedResponse;
import com.bank.products.cards.CreditCard;
import com.bank.products.cards.DebitCard;
import com.bank.products.deposits.Deposit;
import com.bank.service.BankProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing bank products.
 */
@RestController
@RequestMapping("/api/products")
public class BankProductController {

  private final BankProductService service;

  public BankProductController(BankProductService service) {
    this.service = service;
  }

  /**
   * Creates a new debit card.
   *
   * @param request the request body containing the debit card details
   * @return a response entity with the result of the operation
   */
  @PostMapping("/create/debit")
  public ResponseEntity<ProductCreatedResponse> createDebitCard(
      @RequestBody CreateDebitCardRequest request) {
    DebitCard card = new DebitCard(request.getName(), request.getCurrency(), 0);
    service.registerProduct(request.getId(), card);
    return ResponseEntity.ok(
        new ProductCreatedResponse(request.getId(), "Debit card created successfully"));
  }

  /**
   * Creates a new credit card.
   *
   * @param request the request body containing the credit card details
   * @return a response entity with the result of the operation
   */
  @PostMapping("/create/credit")
  public ResponseEntity<ProductCreatedResponse> createCreditCard(
      @RequestBody CreateCreditCardRequest request) {
    CreditCard card = new CreditCard(request.getName(), request.getCurrency(), 0,
        request.getInterestRate());
    service.registerProduct(request.getId(), card);
    return ResponseEntity.ok(
        new ProductCreatedResponse(request.getId(), "Credit card created successfully"));
  }

  /**
   * Creates a new deposit.
   *
   * @param request the request body containing the deposit details
   * @return a response entity with the result of the operation
   */
  @PostMapping("/create/deposit")
  public ResponseEntity<ProductCreatedResponse> createDeposit(
      @RequestBody CreateDepositRequest request) {
    Deposit deposit = new Deposit(request.getName(), request.getCurrency(), request.getBalance());
    service.registerProduct(request.getId(), deposit);
    return ResponseEntity.ok(
        new ProductCreatedResponse(request.getId(), "Deposit created successfully"));
  }

  /**
   * Deposits a given amount to a product.
   *
   * @param id     the id of the product
   * @param amount the amount to deposit
   * @return a response entity with the result of the operation
   */
  @PostMapping("/{id}/deposit")
  public ResponseEntity<String> deposit(@PathVariable String id, @RequestParam double amount) {
    service.deposit(id, amount);
    return ResponseEntity.ok("Deposited " + amount + " to " + id);
  }

  /**
   * Withdraws a given amount from a product.
   *
   * @param id     the id of the product
   * @param amount the amount to withdraw
   * @return a response entity with the result of the operation
   */
  @PostMapping("/{id}/withdraw")
  public ResponseEntity<String> withdraw(@PathVariable String id, @RequestParam double amount) {
    service.withdraw(id, amount);
    return ResponseEntity.ok("Withdrawn " + amount + " from " + id);
  }

  /**
   * Retrieves the balance of a product.
   *
   * @param id the id of the product
   * @return the balance of the product
   */
  @GetMapping("/{id}/balance")
  public ResponseEntity<Double> getBalance(@PathVariable String id) {
    return ResponseEntity.ok(service.getBalance(id));
  }
}
