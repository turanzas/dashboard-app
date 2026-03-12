package com.dashboard.app.kafka.config.data

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "kafka-producer-config")
data class KafkaProducerConfigData(
    var keySerializerClass: String = "",
    var valueSerializerClass: String = "",
    var compressionType: String = "",
    var acks: String = "",
    var batchSize: Int = 0,
    var batchSizeBoostFactor: Int = 0,
    var lingerMs: Int = 0,
    var requestTimeoutMs: Int = 0,
    var retryCount: Int = 0,
)