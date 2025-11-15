package com.bank.controller.dto;

import lombok.Data;

/**
 * Data transfer object for creating a debit card.
 */
@Data
public class CreateDebitCardRequest {
  private String id;
  private String currency;
  private String name;
}
