package com.dashboard.app.common.domain.model.exception

/**
 * Base class for all exceptions related to the domain layer.
 */
abstract class DomainException(message: String) : RuntimeException(message)