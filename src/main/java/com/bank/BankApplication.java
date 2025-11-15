package com.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the Bank Application.
 */
@SpringBootApplication
public class BankApplication {

  /**
   * The main method, which uses Spring Boot's SpringApplication.run() to launch the application.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(BankApplication.class, args);
  }
}