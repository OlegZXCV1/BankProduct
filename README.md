# Bank Products

This project provides a Spring Boot-based architecture for managing various banking products. It's designed to be extensible, allowing for the addition of new products with minimal changes to the existing structure.

## Features

*   **Object-Oriented Design:** The architecture is built using object-oriented principles, with a clear hierarchy of classes representing different bank products.
*   **Extensible:** The design allows for the easy addition of new bank products in the future.
*   **REST API:** A RESTful API is provided for interacting with the bank products.
*   **Unit Tests:** The project includes a comprehensive suite of unit tests to ensure the correctness of the implementation.
*   **Allure Reporting:** Allure is integrated for generating detailed and interactive test reports.

## Requirements

*   Java 17 or higher
*   Maven 3.6.3 or higher

## Getting Started

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/your-username/BankProduct.git
    ```
2.  **Navigate to the project directory:**
    ```bash
    cd BankProduct
    ```
3.  **Build the project:**
    ```bash
    mvn clean install
    ```

## Usage

### Running Tests

To run the unit tests, execute the following command:

```bash
mvn clean test
```

### Generating Allure Report

To generate an Allure report, run the following command:

```bash
mvn allure:report
```

### Viewing the Allure Report

To view the Allure report in your browser, use the following command:

```bash
mvn allure:serve
```