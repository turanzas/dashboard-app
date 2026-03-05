# Standalone Infrastructure

This directory contains the necessary configuration files to run a standalone instance of the application using Docker Compose.

## Components

### Data Layer
- **PostgresSQL**: A relational database management system.
- **Adminer**: A full-featured database management tool for managing PostgresSQL databases.
- **MongoDB**: A NoSQL database for handling unstructured data.
- **Mongo Express**: A web-based MongoDB admin interface.

### Security Layer
- **Keycloak**: An open-source identity and access management solution for modern applications and services.
```bash
docker compose -f docker-compose.yml up keycloak -d
```

### Orchestration Layer
- **Spring Cloud Config Server**: Manages external configurations for applications across all environments.
- **Eureka Server**: A service registry for resilient mid-tier load balancing and failover
- **Api Gateway**: Routes requests to appropriate backend services.

### Backend Layer
- **Financial Entity Service**: Manages financial entity data.

### Frontend Layer

## Commands

Run the full environment locally using Docker Compose.
```bash
docker compose -f docker-compose.yml up -d
```

Stop the full environment locally using Docker Compose.
```bash
docker compose -f docker-compose.yml down --volumes
```

