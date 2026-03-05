package com.dashboard.app.common.domain.model.event.publisher

import com.dashboard.app.common.domain.model.event.DomainEvent

fun interface DomainEventPublisher<T> where T : DomainEvent<Any> {

    fun publish(event: T)

}