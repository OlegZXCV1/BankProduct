package com.bank.service;

import com.bank.products.BankProduct;
import com.bank.products.behaviors.Depositable;
import com.bank.products.behaviors.Withdrawable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BankProductService {
    private final Map<String, BankProduct> products = new HashMap<>();

    public void registerProduct(String id, BankProduct product) {
        products.put(id, product);
    }

    public BankProduct getProduct(String id) {
        return products.get(id);
    }

    public double getBalance(String id) {
        return products.get(id).getBalance();
    }

    public void deposit(String id, double amount) {
        BankProduct product = products.get(id);
        if (product instanceof Depositable d) {
            d.deposit(amount);
        } else {
            throw new UnsupportedOperationException("This product cannot accept deposits");
        }
    }

    public void withdraw(String id, double amount) {
        BankProduct product = products.get(id);
        if (product instanceof Withdrawable w) {
            w.withdraw(amount);
        } else {
            throw new UnsupportedOperationException("This product does not support withdrawals");
        }
    }
}
