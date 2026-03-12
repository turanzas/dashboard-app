package com.dashboard.app.financial.entity.infrastructure.messaging.kafka.publisher

import com.dashboard.app.financial.entity.application.config.FinancialEntityApplicationServiceConfigData
import com.dashboard.app.financial.entity.application.ports.output.message.FinancialEntityCreatedMessagePublisher
import com.dashboard.app.financial.entity.domain.model.event.FinancialEntityCreatedEvent
import com.dashboard.app.financial.entity.infrastructure.messaging.kafka.mapper.FinancialEntityMessagingDataMapper
import com.dashboard.app.kafka.account.avro.model.FinancialEntityCreatedAvroModel
import com.dashboard.app.kafka.producer.service.KafkaProducer
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

val log = KotlinLogging.logger { }

@Component
class FinancialEntityCreatedKafkaMessagePublisher(
    val financialEntityMessagingDataMapper: FinancialEntityMessagingDataMapper,
    val financialEntityApplicationServiceConfigData: FinancialEntityApplicationServiceConfigData,
    val kafkaProducer: KafkaProducer<String, FinancialEntityCreatedAvroModel>,
): FinancialEntityCreatedMessagePublisher {

    override fun publish(event: FinancialEntityCreatedEvent) {
        log.info { "Publishing financial entity created event with id: ${event.financialEntity.id}" }
        val avroModel = financialEntityMessagingDataMapper.toMessage(event)
        kafkaProducer.send(
            financialEntityApplicationServiceConfigData.financialEntityCreatedTopicName,
            event.financialEntity.id.value.toString(),
            avroModel,
            getKafkaCallback(financialEntityApplicationServiceConfigData.financialEntityCreatedTopicName, avroModel)
        )
    }

    private fun getKafkaCallback(
        financialEntityCreatedTopicName: String,
        avroModel: FinancialEntityCreatedAvroModel
    ): CompletableFuture<SendResult<String, FinancialEntityCreatedAvroModel>> =
        CompletableFuture<SendResult<String, FinancialEntityCreatedAvroModel>>().whenComplete { sendResult, throwable ->
            if (throwable != null) {
                log.error(throwable) { "Failed to send message=$avroModel to topic=$financialEntityCreatedTopicName" }
            } else {
                val recordMetadata = sendResult.recordMetadata
                log.info {
                    "Received successful response from Kafka for " +
                        "FinancialEntityId: ${avroModel.id} " +
                        "Topic: ${recordMetadata.topic()} " +
                        "Partition: ${recordMetadata.partition()} " +
                        "Offset ${recordMetadata.offset()} " +
                        "Timestamp: ${recordMetadata.timestamp()} "
                }
            }
        }

}