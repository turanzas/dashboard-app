# Financial Entity

### Actuator

Show endpoints
```shell
curl -X GET http://localhost:8080/actuator
```

To get the status and health of the Financial Entity application, you can use the following Actuator endpoints:
```shell
curl -X GET http://localhost:8080/actuator/health
```
```shell
curl -X GET http://localhost:8080/actuator/info
```

To gracefully shut down the Financial Entity application, you can use the Actuator shutdown endpoint. Make sure that the Actuator is enabled and the shutdown endpoint is exposed in your application's configuration.
```shell
curl -X POST http://localhost:8080/actuator/shutdown
```

### Financial Entity API
The Financial Entity API provides endpoints to manage financial entities.

Documentation:
http://localhost:8080/swagger-ui/index.html

Below are the available endpoints:
- `GET /financial-entities`: Retrieve a list of all financial entities.
    ```shell
    curl -X GET http://localhost:8080/financial-entities
    ```