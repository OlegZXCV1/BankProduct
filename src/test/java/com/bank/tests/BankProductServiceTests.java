package com.bank.tests;

import com.bank.products.cards.DebitCard;
import com.bank.service.BankProductService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BankProductServiceTests {

    private final BankProductService service = new BankProductService();

    @Test
    void testRegisterAndDeposit() {
        DebitCard card = new DebitCard("Test Card", "USD", 100);
        service.registerProduct("c1", card);
        service.deposit("c1", 50);
        assertEquals(150, service.getBalance("c1"));
    }

    @Test
    void testWithdraw() {
        DebitCard card = new DebitCard("Test Card", "USD", 200);
        service.registerProduct("c2", card);
        service.withdraw("c2", 50);
        assertEquals(150, service.getBalance("c2"));
    }
}
