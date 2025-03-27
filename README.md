# The Java Bank

The Java Bank is a simple banking application built with **Spring Boot** and **MySQL**. It supports basic banking operations like account creation, balance enquiry, credit, debit, fund transfer, and transaction history generation with PDF generation.

## Features
- User Account Creation
- Balance Enquiry
- Credit & Debit Operations
- Funds Transfer between Accounts
- Generate Bank Statement (PDF) and Send via Email

## Technologies Used
- **Java (Spring Boot)**
- **MySQL**
- **JPA / Hibernate**
- **Swagger (OpenAPI)**
- **iText (PDF Generation)**
- **JavaMail (Sending Emails)**

## Getting Started

### Prerequisites
- Java 20
- Maven
- MySQL Server

### Configuration
In your `application.properties` file, add the following:

```properties
spring.application.name=the-java-bank
spring.datasource.url=jdbc:mysql://localhost:3306/tja_bank
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_email_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

### Database Setup
1. Create a MySQL database named `tja_bank`.
2. Run the application to allow JPA to create the required tables.

### Running the Application
```bash
mvn spring-boot:run
```

### Swagger UI
Access the API documentation at:
```
http://localhost:8080/swagger-ui/index.html
```

## Usage
Use tools like **Postman** or **cURL** to test the endpoints.

