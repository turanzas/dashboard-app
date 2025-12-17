# Configuration Server

This configuration server is designed to manage and serve configuration files for various applications. 

It provides a centralized way to store, retrieve, and update configuration settings.

## Commands

### Actuator
- **Health Check**
  ```bash
  curl -X GET http://localhost:8070/actuator/health
  ```

### Docker
- **Build the Docker Image**
  ```bash
  ./gradlew jib
  ```

### Configuration files
- **Retrieve Configuration File**
  ```bash
  curl -X GET http://localhost:8070/{application}/{profile}/{label}
  ```
  - Replace `{application}` with the name of your application.
  - Replace `{profile}` with the desired profile (e.g., `dev`, `prod`).
  - Replace `{label}` with the version label (e.g., `main`, `v1.0`).
- ** Retrieve Configuration File for Eureka Client**
  ```bash
  curl -X GET http://localhost:8070/eureka-server.yml
  ```