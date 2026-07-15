# E-Commerce Order Management Microservices

## About

This project is a hands-on microservices application that I'm building
to learn and demonstrate modern backend development using Spring Boot
and Spring Cloud.

Instead of starting with a fully distributed system, every service is
developed and tested independently first. Once each service is stable,
they are connected together using Spring Cloud components and later
moved from synchronous communication (Feign Client) to asynchronous
event-driven communication using Kafka.

The goal of this project is to understand how production-style
microservices are designed, deployed, monitored, and maintained.

---

## Tech Stack

### Backend

- Java 21
- Spring Boot
- Spring Data JPA
- Spring Security
- Spring Cloud

### Databases

- MySQL
- PostgreSQL
- MongoDB

### Communication

- OpenFeign
- Apache Kafka

### Cloud Components

- Spring Cloud Config Server
- Spring Cloud Gateway

### Resilience

- Resilience4j
- Retry
- Circuit Breaker
- Rate Limiter

### Containerization

- Docker
- Docker Compose
- Environment variables using `.env`

### Documentation & Testing

- Swagger / OpenAPI
- JUnit
- Postman

---

# Architecture

Client

↓

API Gateway

↓

User Service

Product Service

Order Service

Inventory Service

Payment Service

Notification Service

↓

Separate Database for every service

↓

Kafka (Event Driven Communication)

---

## Services

### User Service

Responsible for authentication and user management.

Features: - User Registration - Login with JWT - Update Profile - Change
Password - Get Current User - Admin User Management

Database: - MySQL

---

### Product Service

Responsible for product catalog management.

Features: - Create Product - Update Product - Delete Product - Search
Products - Product Categories - Pagination

Database: - PostgreSQL

---

### Inventory Service

Responsible for inventory management.

Features: - Add Stock - Reserve Stock - Release Stock - Update Stock -
Check Availability - Warehouse Management

Database: - MongoDB

---

### Order Service

Acts as the orchestrator of the system.

Features: - Create Order - Cancel Order - Order History - Order Status -
Parallel Feign Calls using CompletableFuture

Database: - PostgreSQL

---

### Payment Service

Responsible for payment processing.

Features: - Create Payment - Verify Payment - Payment Status - Refund

Database: - MySQL

---

### Notification Service

Handles notifications asynchronously.

Features: - Email Notifications - SMS Notifications - Push
Notifications - Order Confirmation - Payment Confirmation

Database: - MongoDB

---

## Communication Plan

### Phase 1

- REST APIs
- OpenFeign Client

### Phase 2

- Kafka Events

Topics planned: - order-created - payment-completed - stock-updated

---

## Project Roadmap

- [x] Project Planning
- [ ] Create Individual Services
- [ ] Implement CRUD APIs
- [ ] Configure Databases
- [ ] Add JWT Authentication
- [ ] Configure Config Server
- [ ] Configure API Gateway
- [ ] Implement Feign Communication
- [ ] Add Multithreading
- [ ] Introduce Kafka
- [ ] Add Resilience4j
- [ ] Dockerize Services
- [ ] Docker Compose
- [ ] Monitoring
- [ ] Integration Testing

---

## Future Improvements

- Frontend (React+Vite)
- Service Discovery (Eureka)
- Distributed Tracing (Zipkin)
- Prometheus & Grafana
- Centralized Logging
- Kubernetes Deployment
- CI/CD Pipeline

---

## Why I Built This

I wanted a project that covers more than basic CRUD operations. The idea
is to gradually build a production-style microservices application and
learn how services communicate, recover from failures, scale
independently, and work together in a distributed environment.

Every feature is added step by step so the evolution of the architecture
is easy to understand.

---

## License

This project is created for learning and portfolio purposes.
