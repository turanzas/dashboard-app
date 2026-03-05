package com.dashboard.app.common.domain.model.event.publisher

import com.dashboard.app.common.domain.model.event.DomainEvent

fun interface DomainEventPublisher<T> where T : DomainEvent<*> {

    fun publish(event: T)

}