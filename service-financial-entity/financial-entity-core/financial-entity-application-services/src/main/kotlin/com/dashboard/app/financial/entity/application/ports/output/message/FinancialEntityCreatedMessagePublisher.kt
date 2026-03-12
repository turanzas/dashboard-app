package com.dashboard.app.financial.entity.application.ports.output.message

import com.dashboard.app.common.domain.model.event.publisher.DomainEventPublisher
import com.dashboard.app.financial.entity.domain.model.event.FinancialEntityCreatedEvent

/**
 * Interface for publishing messages related to the creation of financial entities.
 */
interface FinancialEntityCreatedMessagePublisher: DomainEventPublisher<FinancialEntityCreatedEvent>