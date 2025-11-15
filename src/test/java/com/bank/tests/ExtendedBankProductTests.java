package com.bank.tests;

import com.bank.products.Mortgage;
import com.bank.products.cards.CreditCard;
import com.bank.products.cards.CurrencyDebitCard;
import com.bank.products.cards.DebitCard;
import com.bank.products.deposits.Deposit;
import com.bank.service.BankProductService;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Bank Products")
@Feature("Extended Bank Product Operations")
class ExtendedBankProductTests {

    private BankProductService service;

    @BeforeEach
    void setup() {
        service = new BankProductService();
    }

    // ====== Debit Card Tests ======
    @Test
    @Story("Debit Card Deposit and Withdraw")
    @Description("This test verifies the deposit and withdraw functionality of a debit card.")
    @Severity(SeverityLevel.CRITICAL)
    void testDebitCardDepositAndWithdraw() {
        createAndRegisterDebitCard("d1", "Debit Card", "USD", 100);

        service.deposit("d1", 50);
        assertEquals(150, service.getBalance("d1"));

        service.withdraw("d1", 30);
        assertEquals(120, service.getBalance("d1"));
    }

    @Test
    @Story("Debit Card Withdraw Too Much")
    @Description("This test verifies that withdrawing more than the balance throws an exception.")
    @Severity(SeverityLevel.NORMAL)
    void testDebitCardWithdrawTooMuch() {
        createAndRegisterDebitCard("d2", "Debit Card", "USD", 100);
        assertThrows(IllegalArgumentException.class, () -> service.withdraw("d2", 150));
    }

    // ====== Currency Debit Card Tests ======
    @Test
    @Story("Currency Debit Card Operations")
    @Description("This test verifies the deposit and withdraw functionality of a currency debit card.")
    @Severity(SeverityLevel.CRITICAL)
    void testCurrencyDebitCardOperations() {
        createAndRegisterCurrencyDebitCard("c1", "Currency Card", "EUR", 200);

        service.deposit("c1", 50);
        assertEquals(250, service.getBalance("c1"));

        service.withdraw("c1", 100);
        assertEquals(150, service.getBalance("c1"));
    }

    // ====== Credit Card Tests ======
    @Test
    @Story("Credit Card Debt and Interest")
    @Description("This test verifies that withdrawing from a credit card increases the debt.")
    @Severity(SeverityLevel.CRITICAL)
    void testCreditCardDebtAndInterest() {
        CreditCard creditCard = createAndRegisterCreditCard("cc1", "Credit Card", "USD", 0, 12.5);

        // Withdraw increases debt
        service.withdraw("cc1", 500);
        assertEquals(500, creditCard.getDebt());
        assertEquals(12.5, creditCard.getInterestRate());
    }

    // ====== Deposit Tests ======
    @Test
    @Story("Deposit and Close")
    @Description("This test verifies the deposit and close functionality of a deposit.")
    @Severity(SeverityLevel.CRITICAL)
    void testDepositDepositAndClose() {
        Deposit deposit = createAndRegisterDeposit("dep1", "Deposit", "USD", 1000);

        service.deposit("dep1", 500);
        assertEquals(1500, service.getBalance("dep1"));

        deposit.close();
        assertTrue(deposit.isClosed());

        // Cannot deposit after closing
        assertThrows(IllegalStateException.class, () -> service.deposit("dep1", 100));
    }

    // ====== Unsupported operations ======
    @Test
    @Story("Unsupported Operations")
    @Description("This test verifies that unsupported operations throw an exception.")
    @Severity(SeverityLevel.NORMAL)
    void testUnsupportedOperations() {
        createAndRegisterDeposit("dep2", "Deposit", "USD", 1000);
        assertThrows(UnsupportedOperationException.class, () -> service.withdraw("dep2", 100));
    }

    // ====== Multiple products management ======
    @Test
    @Story("Multiple Products Management")
    @Description("This test verifies that the service can manage multiple products at the same time.")
    @Severity(SeverityLevel.CRITICAL)
    void testMultipleProducts() {
        DebitCard debitCard = createAndRegisterDebitCard("d1", "Debit Card", "USD", 100);
        CreditCard creditCard = createAndRegisterCreditCard("cc1", "Credit Card", "USD", 0, 15);

        service.deposit("d1", 50);
        assertEquals(150, service.getBalance("d1"));

        service.withdraw("cc1", 200);
        assertEquals(200, creditCard.getDebt());
    }

    // ====== Mortgage Tests ======
    @Test
    @Story("Mortgage Deposit and Withdraw")
    @Description("This test verifies the deposit and withdraw functionality of a mortgage.")
    @Severity(SeverityLevel.CRITICAL)
    void testMortgageDepositAndWithdraw() {
        createAndRegisterMortgage("m1", "Mortgage", "USD", 200000);

        service.deposit("m1", 1000);
        assertEquals(199000, service.getBalance("m1"));

        assertThrows(IllegalArgumentException.class, () -> service.deposit("m1", -100));
        assertThrows(UnsupportedOperationException.class, () -> service.withdraw("m1", 500));
    }

    @Test
    @Story("Mortgage Deposit Zero")
    @Description("This test verifies that depositing zero throws an exception.")
    @Severity(SeverityLevel.NORMAL)
    void testMortgageDepositZero() {
        createAndRegisterMortgage("m2", "Mortgage", "USD", 200000);
        assertThrows(IllegalArgumentException.class, () -> service.deposit("m2", 0));
    }

    @Test
    @Story("Mortgage Getters")
    @Description("This test verifies the getters of the Mortgage class.")
    @Severity(SeverityLevel.TRIVIAL)
    void testMortgageGetters() {
        Mortgage mortgage = new Mortgage("My Mortgage", "EUR", 150000);
        assertEquals("My Mortgage", mortgage.getName());
        assertEquals("EUR", mortgage.getCurrency());
        assertEquals(150000, mortgage.getBalance());
    }

    @Test
    @Story("Mortgage toString")
    @Description("This test verifies the toString method of the Mortgage class.")
    @Severity(SeverityLevel.TRIVIAL)
    void testMortgageToString() {
        Mortgage mortgage = new Mortgage("Mortgage", "USD", 200000);
        String expected = "AbstractBankProduct{name='Mortgage', currency='USD', balance=200000.0}";
        assertEquals(expected, mortgage.toString());
    }

    // ====== Helper methods ======
    private DebitCard createAndRegisterDebitCard(String id, String name, String currency, double balance) {
        DebitCard debitCard = new DebitCard(name, currency, balance);
        service.registerProduct(id, debitCard);
        return debitCard;
    }

    private CurrencyDebitCard createAndRegisterCurrencyDebitCard(String id, String name, String currency, double balance) {
        CurrencyDebitCard currencyCard = new CurrencyDebitCard(name, currency, balance);
        service.registerProduct(id, currencyCard);
        return currencyCard;
    }

    private CreditCard createAndRegisterCreditCard(String id, String name, String currency, double balance, double interestRate) {
        CreditCard creditCard = new CreditCard(name, currency, balance, interestRate);
        service.registerProduct(id, creditCard);
        return creditCard;
    }

    private Deposit createAndRegisterDeposit(String id, String name, String currency, double balance) {
        Deposit deposit = new Deposit(name, currency, balance);
        service.registerProduct(id, deposit);
        return deposit;
    }

    private Mortgage createAndRegisterMortgage(String id, String name, String currency, double balance) {
        Mortgage mortgage = new Mortgage(name, currency, balance);
        service.registerProduct(id, mortgage);
        return mortgage;
    }
}
