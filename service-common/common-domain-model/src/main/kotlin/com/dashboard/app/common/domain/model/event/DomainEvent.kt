package com.dashboard.app.common.domain.model.event

interface DomainEvent<T> {
    val data: T
}