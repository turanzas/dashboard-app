package com.dashboard.app.common.domain.model.event

import java.time.ZonedDateTime

/**
 * Represents a domain event that occurs within the application.
 *
 * @param T The type of data associated with the event.
 */
abstract class DomainEvent<T>(
    val occurredAt: ZonedDateTime = ZonedDateTime.now()
)