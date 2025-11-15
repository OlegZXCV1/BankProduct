package com.bank.controller.dto;

import lombok.Data;

/**
 * Data transfer object for creating a credit card.
 */
@Data
public class CreateCreditCardRequest {
  private String id;
  private String currency;
  private String name;
  private double interestRate;
}
