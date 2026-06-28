# FinTrack 💰

FinTrack is a Spring Boot REST API that helps students manage their personal finances by tracking income and expenses. It provides user management, transaction management, and lays the foundation for secure authentication using Spring Security and JWT.

---

## Features

* User Registration
* User Management (CRUD)
* Income & Expense Tracking
* Transaction Management (CRUD)
* Password Encryption using BCrypt
* RESTful API Design
* MySQL Database Integration
* Spring Data JPA
* Input Validation using Jakarta Validation
* Ready for JWT Authentication

---

## Tech Stack

### Backend

* Java 21 (or your installed version)
* Spring Boot
* Spring Data JPA
* Spring Security
* Hibernate
* Maven

### Database

* MySQL

### Utilities

* Lombok
* Jakarta Validation

---

## Project Structure

```
src
│
├── controller
│   ├── UserController
│   └── TransactionController
│
├── model
│   ├── User
│   ├── Transaction
│   └── TransactionType
│
├── repository
│   ├── UserRepository
│   └── TransactionRepository
│
├── request
│   ├── UserCreateRequest
│   └── TransactionCreateRequest
│
├── service
│   ├── UserService
│   └── TransactionService
│
└── FintrackApplication
```

---

## Database Design

### User

| Field      | Type      |
| ---------- | --------- |
| id         | Long      |
| name       | String    |
| email      | String    |
| password   | String    |
| college    | String    |
| joinedDate | LocalDate |

Relationship

```
One User
    |
    |
Many Transactions
```

---

### Transaction

| Field       | Type            |
| ----------- | --------------- |
| id          | Long            |
| title       | String          |
| description | String          |
| amount      | double          |
| type        | TransactionType |
| date        | LocalDate       |
| user        | User            |

---

## Transaction Types

```java
INCOME
EXPENSE
```

---

## REST API

### User Endpoints

| Method | Endpoint                 | Description       |
| ------ | ------------------------ | ----------------- |
| GET    | /api/users               | Get all users     |
| GET    | /api/users/{id}          | Get user by ID    |
| GET    | /api/users/email/{email} | Get user by email |
| POST   | /api/users               | Register a user   |
| PUT    | /api/users/{id}          | Update user       |
| DELETE | /api/users/{id}          | Delete user       |

---

### Transaction Endpoints

| Method | Endpoint                        | Description                    |
| ------ | ------------------------------- | ------------------------------ |
| GET    | /api/transactions               | Get all transactions           |
| GET    | /api/transactions/{id}          | Get transaction by ID          |
| GET    | /api/transactions/user/{userId} | Get all transactions of a user |
| POST   | /api/transactions               | Create transaction             |
| PUT    | /api/transactions/{id}          | Update transaction             |
| DELETE | /api/transactions/{id}          | Delete transaction             |

---

## Sample Request

### Create User

```json
{
    "name": "John Doe",
    "email": "john@example.com",
    "password": "password123",
    "college": "ABC University"
}
```

---

### Create Transaction

```json
{
    "title": "Lunch",
    "description": "College Cafeteria",
    "amount": 150,
    "type": "EXPENSE",
    "date": "2026-06-29",
    "userId": 1
}
```

---

## Running the Project

### Clone Repository

```bash
git clone https://github.com/yourusername/fintrack.git
```

### Configure MySQL

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/fintrack
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Run

```bash
mvn spring-boot:run
```

or

Run `FintrackApplication.java` from your IDE.

---

## Future Improvements

* JWT Authentication
* Login Endpoint
* Role-Based Authorization
* Monthly Expense Reports
* Budget Tracking
* Category Support
* Search & Filtering
* Pagination
* Global Exception Handling
* Swagger/OpenAPI Documentation
* Docker Support
* Unit & Integration Testing

---

## Learning Outcomes

This project demonstrates:

* Spring Boot REST API Development
* Layered Architecture
* Spring Data JPA
* Hibernate ORM
* Entity Relationships
* Dependency Injection
* Password Encryption
* Request Validation
* CRUD Operations
* Maven Project Structure

---

## Author

**Muhammed Irfan K. M.**

Computer Science Engineering Student

Spring Boot | Java | Backend Development
