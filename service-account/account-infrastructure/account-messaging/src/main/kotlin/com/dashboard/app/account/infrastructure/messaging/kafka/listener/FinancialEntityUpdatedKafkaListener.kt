package com.dashboard.app.account.infrastructure.messaging.kafka.listener

import com.dashboard.app.account.application.port.input.message.FinancialEntityUpdatedMessageListener
import com.dashboard.app.account.infrastructure.messaging.kafka.mapper.FinancialEntityMessagingDataMapper
import com.dashboard.app.kafka.account.avro.model.FinancialEntityUpdatedAvroModel
import com.dashboard.app.kafka.consumer.service.KafkaConsumer
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {  }

@Component
class FinancialEntityUpdatedKafkaListener(
    val financialEntityUpdatedMessageListener: FinancialEntityUpdatedMessageListener,
    val financialEntityMessagingDataMapper: FinancialEntityMessagingDataMapper,
): KafkaConsumer<FinancialEntityUpdatedAvroModel> {

    @KafkaListener(
        id = "\${kafka-consumer-config.financial-entity-consumer-group-id}",
        topics = ["\${account-service.financial-entity-updated-topic-name}"],
    )
    override fun receive(
        @Payload messages: List<FinancialEntityUpdatedAvroModel>,
        @Header(KafkaHeaders.RECEIVED_KEY) keys: List<String>,
        @Header(KafkaHeaders.RECEIVED_PARTITION) partitions: List<Int>,
        @Header(KafkaHeaders.OFFSET) offsets: List<Long>
    ) {
        log.info { "Received ${messages.size} financial entity updated messages with keys: $keys, partitions: $partitions, offsets: $offsets" }

        messages.forEach { financialEntityUpdatedAvroModel ->
            log.info { "Processing financial entity updated message with key: ${financialEntityUpdatedAvroModel.id}" }
            val financialEntity = financialEntityMessagingDataMapper.toUpdatedApplication(financialEntityUpdatedAvroModel)
            financialEntityUpdatedMessageListener.process(financialEntity)
        }
    }

}