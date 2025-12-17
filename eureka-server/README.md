# Eureka Server

Eureka is a service discovery server that allows microservices to register themselves and discover other services.

Once it's running, you can access the Eureka dashboard at: [http://localhost:8071](http://localhost:8071)

## Commands

### Running Locally
- **Get eureka registered applications**
  ```bash
  curl -X GET http://localhost:8071/eureka/apps
  ```
  ```bash
  curl -X GET http://localhost:8071/eureka/apps/FINANCIAL-ENTITY-SERVICE/financial-entities
  ```

### Docker

- **Build the Docker Image**
  ```bash
  ./gradlew jib
  ```