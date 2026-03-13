package com.dashboard.app.kafka.consumer.service

import org.apache.avro.specific.SpecificRecordBase

fun interface KafkaConsumer<T> where T: SpecificRecordBase {

    fun receive(messages: List<T>, keys: List<String>, partitions: List<Int>, offsets: List<Long>)

}