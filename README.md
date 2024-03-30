# Sales-Management-System - An Backend Api


## Features
### Products Management
- Fetch products and view id, name, description, category, creation date, available quantity, and price.
- Create a new product with a name, description, category, initial quantity, and price.
- Update the existing product's name, description, category, quantity, and price.
- Delete a product from the inventory.

### Clients Management
- Fetch clients and view id, name, last name, mobile, email, and address.
- Create a new client with name, last name, mobile, email, and address.
- Update the existing client's name, last name, mobile, email, and address.
- Delete a client from the database.

### Sales
- Fetch all sales operations and view id, creation date, client, seller, and total.
- Create new sales with multiple transactions.
- Edit quantities and prices of the sale.

### Reporting
- Generate a sales report for a specific date range, including the total number of sales, total revenue, top-selling products, and top-performing sellers.
- Generate a client report showing the total number of clients, top-spending clients, client activity, and client location statistics.
- Generate a product report with inventory status, sales performance, and pricing analysis.

This format should be more readable and suitable for platforms like GitHub.

## Technologies Used
  - Backend
    - Java 17
    - Spring Boot
    - Logging and Auditing
    - Spring Data JPA
    - Spring MVC
    - MySQL (for production)

## Setup
### Prerequisites
- JDK 17 or higher
- Maven
- MySQL database

1. Clone this repository to your local machine:
    ```bash
    git clone https://github.com/your-username/Sales-Management-System.git
    cd Sales-Management-System
    ```
2. Configure MySQL database settings in application.properties under `src/main/resources`.
3. Build and run the backend using Maven:
    ```bash
    mvn spring-boot:run
    ```

## Contributing
Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.
