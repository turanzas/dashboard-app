package com.dashboard.app.kafka.config.data

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "kafka-config")
data class KafkaConfigData(
    var bootstrapServers: String = "",
    var schemaRegistryUrlKey: String = "",
    var schemaRegistryUrl: String = "",
    var numOfPartitions: Int = 0,
    var replicationFactor: Short = 0,
)