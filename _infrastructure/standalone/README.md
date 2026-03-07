# Standalone Infrastructure

This directory contains the necessary configuration files to run a standalone instance of the application using Docker Compose.

## Components

### Data Layer
- **PostgresSQL**: A relational database management system.
- **Adminer**: A full-featured database management tool for managing PostgresSQL databases.
- **MongoDB**: A NoSQL database for handling unstructured data.
- **Mongo Express**: A web-based MongoDB admin interface.

### Messaging Layer
- **Kafka**: A distributed event streaming platform used for building real-time data pipelines and streaming applications.
```bash
docker compose -f docker-compose-common.yml -f docker-compose-kafka.yml up -d
```
- **Kafka Init**: A service to initialize Kafka topics and configurations.
```bash
docker compose -f docker-compose-common.yml -f docker-compose-kafka-init.yml up -d
```
- **Kafka CMAK**: A web-based tool for managing and monitoring Kafka clusters.
CMAK (Cluster Manager for Apache Kafka) provides an interface to manage and monitor Kafka clusters, including viewing cluster status, managing topics, and monitoring consumer groups.

http://localhost:9000

Add cluster with the following configuration:
- Cluster Name: `dashboard-app`
- Cluster Zookeeper Hosts: `zookeeper:2181`
- Save
- Go to cluster and click on "Topics" to see the list of topics created by Kafka Init service.

### Security Layer
- **Keycloak**: An open-source identity and access management solution for modern applications and services.
```bash
docker compose -f docker-compose-common.yml -f docker-compose-server.yml up keycloak -d
```

### Orchestration Layer
- **Spring Cloud Config Server**: Manages external configurations for applications across all environments.
- **Eureka Server**: A service registry for resilient mid-tier load balancing and failover
- **Api Gateway**: Routes requests to appropriate backend services.

### Backend Layer
- **Account Service**: Manages accounts' data.
- **Card Service**: Manages cards' data.
- **Financial Entity Service**: Manages financial entities' data.
- **Transaction Service**: Manages transactions' data.

### Frontend Layer

## Commands

Run the full environment locally using Docker Compose.
```bash
docker compose -f docker-compose-backend.yml up -d
```

Stop the full environment locally using Docker Compose.
```bash
docker compose -f docker-compose-backend.yml down --volumes
```

