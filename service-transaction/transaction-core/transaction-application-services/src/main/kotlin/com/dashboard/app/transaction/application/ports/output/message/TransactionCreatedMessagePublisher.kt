package com.dashboard.app.transaction.application.ports.output.message

import com.dashboard.app.common.domain.model.event.publisher.DomainEventPublisher
import com.dashboard.app.transaction.domain.model.event.TransactionCreatedEvent

interface TransactionCreatedMessagePublisher: DomainEventPublisher<TransactionCreatedEvent>