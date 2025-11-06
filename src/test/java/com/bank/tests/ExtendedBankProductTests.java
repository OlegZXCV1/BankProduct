package com.bank.tests;

import com.bank.products.cards.*;
import com.bank.products.deposits.Deposit;
import com.bank.service.BankProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExtendedBankProductTests {

    private BankProductService service;

    @BeforeEach
    void setup() {
        service = new BankProductService();
    }

    // ====== Debit Card Tests ======
    @Test
    void testDebitCardDepositAndWithdraw() {
        DebitCard debitCard = new DebitCard("Debit Card", "USD", 100);
        service.registerProduct("d1", debitCard);

        service.deposit("d1", 50);
        assertEquals(150, service.getBalance("d1"));

        service.withdraw("d1", 30);
        assertEquals(120, service.getBalance("d1"));
    }

    @Test
    void testDebitCardWithdrawTooMuch() {
        DebitCard debitCard = new DebitCard("Debit Card", "USD", 100);
        service.registerProduct("d2", debitCard);

        assertThrows(IllegalArgumentException.class, () -> service.withdraw("d2", 150));
    }

    // ====== Currency Debit Card Tests ======
    @Test
    void testCurrencyDebitCardOperations() {
        CurrencyDebitCard currencyCard = new CurrencyDebitCard("Currency Card", "EUR", 200);
        service.registerProduct("c1", currencyCard);

        service.deposit("c1", 50);
        assertEquals(250, service.getBalance("c1"));

        service.withdraw("c1", 100);
        assertEquals(150, service.getBalance("c1"));
    }

    // ====== Credit Card Tests ======
    @Test
    void testCreditCardDebtAndInterest() {
        CreditCard creditCard = new CreditCard("Credit Card", "USD", 0, 12.5);
        service.registerProduct("cc1", creditCard);

        // Withdraw increases debt
        service.withdraw("cc1", 500);
        assertEquals(500, creditCard.getDebt());
        assertEquals(12.5, creditCard.getInterestRate());
    }

    // ====== Deposit Tests ======
    @Test
    void testDepositDepositAndClose() {
        Deposit deposit = new Deposit("Deposit", "USD", 1000);
        service.registerProduct("dep1", deposit);

        service.deposit("dep1", 500);
        assertEquals(1500, service.getBalance("dep1"));

        deposit.close();
        assertTrue(deposit.isClosed());

        // Cannot deposit after closing
        assertThrows(IllegalStateException.class, () -> service.deposit("dep1", 100));
    }

    // ====== Unsupported operations ======
    @Test
    void testUnsupportedOperations() {
        Deposit deposit = new Deposit("Deposit", "USD", 1000);
        service.registerProduct("dep2", deposit);

        // Withdraw not supported
        assertThrows(UnsupportedOperationException.class, () -> service.withdraw("dep2", 100));
    }

    // ====== Multiple products management ======
    @Test
    void testMultipleProducts() {
        DebitCard debitCard = new DebitCard("Debit Card", "USD", 100);
        CreditCard creditCard = new CreditCard("Credit Card", "USD", 0, 15);

        service.registerProduct("d1", debitCard);
        service.registerProduct("cc1", creditCard);

        service.deposit("d1", 50);
        assertEquals(150, service.getBalance("d1"));

        service.withdraw("cc1", 200);
        assertEquals(200, creditCard.getDebt());
    }
}
