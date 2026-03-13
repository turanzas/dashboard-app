package com.dashboard.app.account.infrastructure.messaging.kafka.listener

import com.dashboard.app.account.application.port.input.message.FinancialEntityCreatedMessageListener
import com.dashboard.app.account.infrastructure.messaging.kafka.mapper.FinancialEntityMessagingDataMapper
import com.dashboard.app.kafka.account.avro.model.FinancialEntityCreatedAvroModel
import com.dashboard.app.kafka.consumer.service.KafkaConsumer
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {  }

@Component
class FinancialEntityCreatedKafkaListener(
    val financialEntityCreatedMessageListener: FinancialEntityCreatedMessageListener,
    val financialEntityMessagingDataMapper: FinancialEntityMessagingDataMapper,
): KafkaConsumer<FinancialEntityCreatedAvroModel> {

    @KafkaListener(
        id = "\${kafka-consumer-config.financial-entity-consumer-group-id}",
        topics = ["\${financial-entity-service.financial-entity-created-topic-name}"],
    )
    override fun receive(
        @Payload messages: List<FinancialEntityCreatedAvroModel>,
        @Header(KafkaHeaders.RECEIVED_KEY) keys: List<Long>,
        @Header(KafkaHeaders.RECEIVED_PARTITION) partitions: List<Int>,
        @Header(KafkaHeaders.OFFSET) offsets: List<Long>
    ) {
        log.info { "Received ${messages.size} financial entity created messages with keys: $keys, partitions: $partitions, offsets: $offsets" }

        messages.forEach { financialEntityCreatedAvroModel ->
            log.info { "Processing financial entity created message with key: ${financialEntityCreatedAvroModel.id}" }
            val financialEntity = financialEntityMessagingDataMapper.toCreatedApplication(financialEntityCreatedAvroModel)
            financialEntityCreatedMessageListener.process(financialEntity)
        }
    }

}