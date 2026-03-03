package com.dashboard.app.account.domain.model.exception

/**
 * Represents an illegal status of an {@link com.dashboard.app.account.domain.model.Account}
 */
class IllegalStatusException: RuntimeException {
    constructor(message: String) : super(message)
}