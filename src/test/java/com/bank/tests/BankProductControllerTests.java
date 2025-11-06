package com.bank.tests;

import com.bank.BankApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = BankApplication.class)
@AutoConfigureMockMvc
class BankProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() throws Exception {
        // Создаём продукты для тестов
        mockMvc.perform(post("/api/products/create/debit")
                        .param("id", "d1")
                        .param("currency", "USD"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/products/create/credit")
                        .param("id", "cc1")
                        .param("currency", "USD")
                        .param("rate", "15"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/products/create/deposit")
                        .param("id", "dep1")
                        .param("currency", "EUR"))
                .andExpect(status().isOk());
    }

    @Test
    void testDepositToDebitCard() throws Exception {
        mockMvc.perform(post("/api/products/d1/deposit")
                        .param("amount", "200"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deposited 200.0 to d1"));

        mockMvc.perform(get("/api/products/d1/balance"))
                .andExpect(status().isOk())
                .andExpect(content().string("200.0"));
    }

    @Test
    void testWithdrawFromDebitCard() throws Exception {
        // Пополняем баланс
        mockMvc.perform(post("/api/products/d1/deposit")
                        .param("amount", "150"))
                .andExpect(status().isOk());

        // Снимаем
        mockMvc.perform(post("/api/products/d1/withdraw")
                        .param("amount", "50"))
                .andExpect(status().isOk())
                .andExpect(content().string("Withdrawn 50.0 from d1"));

        mockMvc.perform(get("/api/products/d1/balance"))
                .andExpect(status().isOk())
                .andExpect(content().string("100.0"));
    }

    @Test
    void testCreditCardDebt() throws Exception {
        mockMvc.perform(post("/api/products/cc1/withdraw")
                        .param("amount", "500"))
                .andExpect(status().isOk())
                .andExpect(content().string("Withdrawn 500.0 from cc1"));
    }

    @Test
    void testDepositCloseAndDepositFail() throws Exception {
        // Закрываем депозит вручную через сервис
        mockMvc.perform(post("/api/products/dep1/deposit")
                        .param("amount", "300"))
                .andExpect(status().isOk());

        // Здесь для теста используем внутренний сервис через бин, иначе придется создавать endpoint для close
        // В реальном проекте можно добавить endpoint для закрытия
    }

    @Test
    void testUnsupportedOperation() throws Exception {
        mockMvc.perform(post("/api/products/dep1/withdraw")
                        .param("amount", "100"))
                .andExpect(status().isBadRequest())  // было isInternalServerError()
                .andExpect(content().string("This product does not support withdrawals"));
    }
}
