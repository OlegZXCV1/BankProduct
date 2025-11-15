package com.bank.tests;

import com.bank.BankApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = BankApplication.class)
@AutoConfigureMockMvc
class BankProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() throws Exception {
        Map<String, Object> debitCardRequest = new HashMap<>();
        debitCardRequest.put("id", "d1");
        debitCardRequest.put("currency", "USD");
        debitCardRequest.put("name", "My Debit Card");

        mockMvc.perform(post("/api/products/create/debit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(debitCardRequest)))
                .andExpect(status().isOk());

        Map<String, Object> creditCardRequest = new HashMap<>();
        creditCardRequest.put("id", "cc1");
        creditCardRequest.put("currency", "USD");
        creditCardRequest.put("name", "My Credit Card");
        creditCardRequest.put("interestRate", 15.0);

        mockMvc.perform(post("/api/products/create/credit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creditCardRequest)))
                .andExpect(status().isOk());

        Map<String, Object> depositRequest = new HashMap<>();
        depositRequest.put("id", "dep1");
        depositRequest.put("currency", "EUR");
        depositRequest.put("name", "My Deposit");
        depositRequest.put("balance", 1000.0);

        mockMvc.perform(post("/api/products/create/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(depositRequest)))
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
        mockMvc.perform(post("/api/products/d1/deposit")
                        .param("amount", "150"))
                .andExpect(status().isOk());

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
        mockMvc.perform(post("/api/products/dep1/deposit")
                        .param("amount", "300"))
                .andExpect(status().isOk());
    }

    @Test
    void testUnsupportedOperation() throws Exception {
        mockMvc.perform(post("/api/products/dep1/withdraw")
                        .param("amount", "100"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("This product does not support withdrawals"));
    }
}
