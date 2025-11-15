package com.bank.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data transfer object for the response of a product creation.
 */
@Data
@AllArgsConstructor
public class ProductCreatedResponse {
  private String id;
  private String message;
}
