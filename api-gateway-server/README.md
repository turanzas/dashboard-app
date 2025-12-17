# API Gateway Server

This is an API Gateway Server built using Spring Cloud Gateway. It serves as a single entry point for routing requests to various microservices in a distributed system.

## Features
- Dynamic routing to microservices
- Load balancing
- Circuit breaker integration
- Request filtering and transformation
- Security features like authentication and authorization

## Commands

### Actuator Endpoints
- Show Endpoints
  ```shell
  curl http://localhost:8072/actuator
  ```
- Health Check
  ```shell
  curl http://localhost:8072/actuator/health
  ```
- Info
  ```shell
  curl http://localhost:8072/actuator/info
  ```

### Financial Entities Service
- Get All Financial Entities
  ```shell
  curl http://localhost:8072/api/financial-entities
  ```