# Financial Entity

### Actuator

To gracefully shut down the Financial Entity application, you can use the Actuator shutdown endpoint. Make sure that the Actuator is enabled and the shutdown endpoint is exposed in your application's configuration.
```shell
curl -X POST http://localhost:8080/actuator/shutdown
```