package com.dashboard.app.kafka.consumer.service

import org.apache.avro.specific.SpecificRecordBase

fun interface KafkaConsumer<T> where T: SpecificRecordBase {

    fun receive(messages: List<T>, keys: List<Long>, partitions: List<Int>, offsets: List<Long>)

}