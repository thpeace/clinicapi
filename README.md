# Clinic API

A Spring Boot REST API for clinic management with **secure authentication** using JWT tokens and BCrypt password hashing.

## Tech Stack

- **Java 17** + **Spring Boot 3.4.5**
- **Spring Security** - Authentication & Authorization
- **JWT (JSON Web Tokens)** - Stateless session management
- **BCrypt** - Password hashing
- **SQL Server** - Database
- **JPA/Hibernate** - ORM
- **Swagger/OpenAPI** - API documentation

## Quick Start

```bash
# Run the application
mvn spring-boot:run

# Or using Maven wrapper
./mvnw spring-boot:run
```

## API Endpoints

### Authentication (Public)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/login` | Login and get JWT token |

### Protected Endpoints

All other endpoints require `Authorization: Bearer <token>` header.

## Authentication Flow

```
1. POST /api/auth/login
   Body: { "username": "admin", "password": "admin123" }

2. Response:
   {
     "token": "eyJhbGciOiJIUzI1...",
     "type": "Bearer",
     "expiresIn": 1800,
     "username": "admin"
   }

3. Use token for protected endpoints:
   Header: Authorization: Bearer eyJhbGciOiJIUzI1...
```

## Configuration

Edit `src/main/resources/application.properties`:

```properties
# Database
spring.datasource.url=jdbc:sqlserver://your-server:1433;databaseName=clinic
spring.datasource.username=sa
spring.datasource.password=your-password

# JWT Settings
jwt.secret=your-256-bit-secret-key
jwt.expiration=1800000  # 30 minutes in milliseconds
```

## Create Test User

Run this SQL to create an admin user (password: `admin123`):

```sql
INSERT INTO users (username, password, role) VALUES 
('admin', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'ADMIN');
```

## Swagger UI

Access API documentation at: `http://localhost:8080/swagger-ui.html`

## Project Structure

```
src/main/java/com/clinic/
├── config/          # Security & Swagger configuration
├── controller/      # REST controllers
├── dto/             # Request/Response DTOs
├── model/           # JPA entities
├── repository/      # Spring Data JPA repositories
├── security/        # JWT & authentication filters
└── service/         # Business logic
```