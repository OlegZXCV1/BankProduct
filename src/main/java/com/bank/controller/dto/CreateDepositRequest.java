package com.bank.controller.dto;

import lombok.Data;

/**
 * Data transfer object for creating a deposit.
 */
@Data
public class CreateDepositRequest {
  private String id;
  private String currency;
  private String name;
  private double balance;
}
