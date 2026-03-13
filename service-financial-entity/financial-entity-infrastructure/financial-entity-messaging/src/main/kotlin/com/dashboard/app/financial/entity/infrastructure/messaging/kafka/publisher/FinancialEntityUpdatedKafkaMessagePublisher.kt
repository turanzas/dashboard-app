package com.dashboard.app.financial.entity.infrastructure.messaging.kafka.publisher

import com.dashboard.app.financial.entity.application.config.FinancialEntityApplicationServiceConfigData
import com.dashboard.app.financial.entity.application.ports.output.message.FinancialEntityUpdatedMessagePublisher
import com.dashboard.app.financial.entity.domain.model.event.FinancialEntityUpdatedEvent
import com.dashboard.app.financial.entity.infrastructure.messaging.kafka.mapper.FinancialEntityMessagingDataMapper
import com.dashboard.app.kafka.account.avro.model.FinancialEntityUpdatedAvroModel
import com.dashboard.app.kafka.producer.service.KafkaProducer
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class FinancialEntityUpdatedKafkaMessagePublisher(
    val financialEntityMessagingDataMapper: FinancialEntityMessagingDataMapper,
    val financialEntityApplicationServiceConfigData: FinancialEntityApplicationServiceConfigData,
    val kafkaProducer: KafkaProducer<String, FinancialEntityUpdatedAvroModel>,
): FinancialEntityUpdatedMessagePublisher {

    override fun publish(event: FinancialEntityUpdatedEvent) {
        log.info { "Publishing financial entity updated event with id: ${event.financialEntity.id}" }
        val avroModel = financialEntityMessagingDataMapper.toUpdatedMessage(event)
        kafkaProducer.send(
            financialEntityApplicationServiceConfigData.financialEntityUpdatedTopicName,
            event.financialEntity.id.value.toString(),
            avroModel,
            getKafkaCallback(financialEntityApplicationServiceConfigData.financialEntityUpdatedTopicName, avroModel)
        )
        log.info { "Message sent to Kafka for financial entity updated event with id: ${event.financialEntity.id}" }
    }

    private fun getKafkaCallback(
        financialEntityUpdatedTopicName: String,
        avroModel: FinancialEntityUpdatedAvroModel
    ) =
        CompletableFuture<SendResult<String, FinancialEntityUpdatedAvroModel>>().whenComplete { sendResult, throwable ->
            if (throwable != null) {
                log.error(throwable) { "Failed to send message=$avroModel to topic=$financialEntityUpdatedTopicName" }
            } else {
                val recordMetadata = sendResult.recordMetadata
                log.info {
                    "Received successful response from Kafka for " +
                        "FinancialEntityId: ${avroModel.id} " +
                        "Topic: ${recordMetadata.topic()} " +
                        "Partition: ${recordMetadata.partition()} " +
                        "Offset ${recordMetadata.offset()} " +
                        "Timestamp: ${recordMetadata.timestamp()} " +
                        "for financial entity updated event"
                }
            }
        }

}