package com.bank.tests;

import io.qameta.allure.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Bank Products")
@Feature("Debit Card Operations")
public class BankProductAllureTests {

    // Пример банковского продукта
    class DebitCard {
        private double balance;
        public DebitCard(String name, String currency, double balance) { this.balance = balance; }
        public void deposit(double amount) { this.balance += amount; }
        public double getBalance() { return balance; }
    }

    @Test
    @Story("Deposit money to debit card")
    @Description("This test deposits money to debit card and checks the balance")
    @Severity(SeverityLevel.CRITICAL)
    void testDebitCardDeposit() {
        // arrange
        DebitCard card = new DebitCard("Debit Card", "USD", 100);
        // act
        card.deposit(50);
        // assert
        assertEquals(150, card.getBalance());
    }
}
