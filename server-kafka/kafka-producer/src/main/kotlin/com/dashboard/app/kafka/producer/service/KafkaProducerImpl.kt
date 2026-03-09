package com.dashboard.app.kafka.producer.service

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.annotation.PreDestroy
import org.apache.avro.specific.SpecificRecordBase
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service
import java.io.Serializable
import java.util.concurrent.CompletableFuture

val logger = KotlinLogging.logger {  }

@Service
class KafkaProducerImpl<K, V>(
    val kafkaTemplate: KafkaTemplate<K, V>
) : KafkaProducer<K, V> where K: Serializable, V: SpecificRecordBase {

    override fun send(topicName: String, key: K, message: V, callback: CompletableFuture<SendResult<K, V>>) {
        logger.info { "Sending message=$message to topic=$topicName" }
        val completableFuture = kafkaTemplate.send(topicName, key, message)
        completableFuture.whenComplete { sendResult, throwable ->
            if (throwable != null) {
                logger.error(throwable) { "Failed to send message=$message to topic=$topicName" }
                callback.completeExceptionally(throwable)
            } else {
                logger.info { "Message sent successfully: $sendResult" }
                callback.complete(sendResult)
            }
        }
    }

    @PreDestroy
    fun close() {
        logger.info { "Closing Kafka producer!" }
        kafkaTemplate.flush()
        kafkaTemplate.destroy()
    }

}