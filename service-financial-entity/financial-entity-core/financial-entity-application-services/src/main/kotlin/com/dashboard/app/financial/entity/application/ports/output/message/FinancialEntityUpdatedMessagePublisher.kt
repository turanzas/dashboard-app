package com.dashboard.app.financial.entity.application.ports.output.message

import com.dashboard.app.common.domain.model.event.publisher.DomainEventPublisher
import com.dashboard.app.financial.entity.domain.model.event.FinancialEntityUpdatedEvent

interface FinancialEntityUpdatedMessagePublisher: DomainEventPublisher<FinancialEntityUpdatedEvent> {
}