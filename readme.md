# JWT Authentication Boilerplate – Spring Boot 2.7.15

## Overview

This project provides a **JWT Authentication Boilerplate** built with **Spring Boot 2.7.15**. It simplifies the process of integrating JWT-based authentication into any Spring Boot project by reusing common configurations and implementations. The goal is to avoid rewriting repetitive JWT logic every time you start a new project.

---

## Features

- **JWT Generation and Validation**: Securely generate and validate JSON Web Tokens (JWT).
- **Global Exception Handling**: Centralized error handling with custom exceptions.
- **User Authentication and Authorization**: Filter incoming requests based on JWT tokens.
- **Spring Security Integration**: Configured with `OncePerRequestFilter` to manage JWT-based authentication.
- **Role Management Support**: Store roles inside JWT for access control.
- **MySQL Database Integration**: Includes a `Users` entity with JPA configuration for persistence.

---

## Getting Started

### Prerequisites

Make sure you have the following installed:

- **Java 11**
- **Maven**
- **MySQL** (or any other relational DB)
- **Postman** (for testing API endpoints)

### Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/abhinav3254/spring-2.7.15-boilerplate.git
   cd spring-jwt-boilerplate
   ```

2. **Configure the Database**:
   Update the `application.properties` file with your database credentials.
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/auth
   spring.datasource.username=root
   spring.datasource.password=your_password
   ```

3. **Run the Application**:
   Use Maven to build and run the project.
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access the APIs**:
   - **Login**: `/auth/login`
   - **Register**: `/auth/register`
   - Other endpoints can be secured by the JWT filter.

---

## Project Structure

```
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com.abhinav3254.jwt      # JWT Logic
│   │   │   ├── com.abhinav3254.model    # JPA Entities
│   │   │   ├── com.abhinav3254.config   # Security Configurations
│   │   │   ├── com.abhinav3254.exception # Global Exception Handling
│   │   ├── resources
│   │       ├── application.properties   # Database Configurations
```

---

## How JWT Works in This Boilerplate

1. **User Login**: The user sends a request with valid credentials to `/auth/login`.
2. **Token Generation**: On successful login, a JWT is generated and returned to the user.
3. **Authorization**: For every subsequent request, the client sends the JWT in the `Authorization` header.
4. **Request Filtering**: The `JWTFilter` checks the token validity and grants access accordingly.

---

## Contributions

Feel free to fork this repository and raise pull requests for any improvements or feature additions.

---

## License

This project is licensed under the MIT License.
