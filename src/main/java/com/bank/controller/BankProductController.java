package com.bank.controller;

import com.bank.products.BankProduct;
import com.bank.products.cards.CreditCard;
import com.bank.products.cards.DebitCard;
import com.bank.products.deposits.Deposit;
import com.bank.service.BankProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class BankProductController {

    private final BankProductService service;

    public BankProductController(BankProductService service) {
        this.service = service;
    }

    @PostMapping("/create/debit")
    public String createDebitCard(@RequestParam String id, @RequestParam String currency) {
        DebitCard card = new DebitCard("My Debit Card", currency, 0);
        service.registerProduct(id, card);
        return "Debit card created with id=" + id;
    }

    @PostMapping("/create/credit")
    public String createCreditCard(@RequestParam String id, @RequestParam String currency, @RequestParam double rate) {
        CreditCard card = new CreditCard("My Credit Card", currency, 0, rate);
        service.registerProduct(id, card);
        return "Credit card created with id=" + id;
    }

    @PostMapping("/create/deposit")
    public String createDeposit(@RequestParam String id, @RequestParam String currency) {
        Deposit deposit = new Deposit("My Deposit", currency, 1000);
        service.registerProduct(id, deposit);
        return "Deposit created with id=" + id;
    }

    @PostMapping("/{id}/deposit")
    public String deposit(@PathVariable String id, @RequestParam double amount) {
        service.deposit(id, amount);
        return "Deposited " + amount + " to " + id;
    }

    @PostMapping("/{id}/withdraw")
    public String withdraw(@PathVariable String id, @RequestParam double amount) {
        service.withdraw(id, amount);
        return "Withdrawn " + amount + " from " + id;
    }

    @GetMapping("/{id}/balance")
    public double getBalance(@PathVariable String id) {
        return service.getBalance(id);
    }
}
