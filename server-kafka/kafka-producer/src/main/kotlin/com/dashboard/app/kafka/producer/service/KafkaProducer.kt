package com.dashboard.app.kafka.producer.service

import org.apache.avro.specific.SpecificRecordBase
import org.springframework.kafka.support.SendResult
import java.io.Serializable
import java.util.concurrent.CompletableFuture

fun interface KafkaProducer<K, V> where K: Serializable, V: SpecificRecordBase {

    fun send(topicName: String, key: K, message: V, callback: CompletableFuture<SendResult<K, V>>)

}