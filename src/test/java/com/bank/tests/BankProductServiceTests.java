package com.bank.tests;

import com.bank.products.cards.DebitCard;
import com.bank.service.BankProductService;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Epic("Bank Products")
@Feature("Bank Product Service")
class BankProductServiceTests {

    private final BankProductService service = new BankProductService();

    @Test
    @Story("Register and Deposit")
    @Description("This test verifies that a product can be registered and then deposited to.")
    @Severity(SeverityLevel.CRITICAL)
    void testRegisterAndDeposit() {
        DebitCard card = new DebitCard("Test Card", "USD", 100);
        service.registerProduct("c1", card);
        service.deposit("c1", 50);
        assertEquals(150, service.getBalance("c1"));
    }

    @Test
    @Story("Withdraw")
    @Description("This test verifies that a product can be withdrawn from.")
    @Severity(SeverityLevel.CRITICAL)
    void testWithdraw() {
        DebitCard card = new DebitCard("Test Card", "USD", 200);
        service.registerProduct("c2", card);
        service.withdraw("c2", 50);
        assertEquals(150, service.getBalance("c2"));
    }
}
