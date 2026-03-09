package com.dashboard.app.kafka.producer

import com.dashboard.app.kafka.config.data.KafkaConfigData
import com.dashboard.app.kafka.config.data.KafkaProducerConfigData
import org.apache.avro.specific.SpecificRecordBase
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import java.io.Serializable

@Configuration
class KafkaProducerConfig<K, V>(
    val kafkaConfigData: KafkaConfigData,
    val kafkaProducerConfigData: KafkaProducerConfigData
) where K : Serializable, V : SpecificRecordBase{

    @Bean
    fun kafkaTemplate(): KafkaTemplate<K, V> = KafkaTemplate(producerFactory())

    fun producerFactory(): ProducerFactory<K, V> = DefaultKafkaProducerFactory(producerConfigs())

    fun producerConfigs(): Map<String, Any> =
        mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaConfigData.bootstrapServers,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to kafkaProducerConfigData.keySerializerClass,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to kafkaProducerConfigData.valueSerializerClass,
            ProducerConfig.BATCH_SIZE_CONFIG to
                    kafkaProducerConfigData.batchSize * kafkaProducerConfigData.batchSizeBoostFactor,
            ProducerConfig.LINGER_MS_CONFIG to kafkaProducerConfigData.lingerMs,
            ProducerConfig.COMPRESSION_TYPE_CONFIG to kafkaProducerConfigData.compressionType,
            ProducerConfig.ACKS_CONFIG to kafkaProducerConfigData.acks,
            ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG to kafkaProducerConfigData.requestTimeoutMs,
            ProducerConfig.RETRIES_CONFIG to kafkaProducerConfigData.retryCount,
            kafkaConfigData.schemaRegistryUrlKey to kafkaConfigData.schemaRegistryUrl,
        )

}