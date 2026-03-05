package com.dashboard.app.transaction.domain.model.exception

import com.dashboard.app.common.domain.model.exception.DomainException

/**
 * Transaction throw when an error occurs in the transaction domain logic.
 * This exception is used to indicate that a business rule has been violated
 * or an unexpected condition has occurred within the transaction domain.
 *
 * @param message the detail message for the exception
 */
class TransactionDomainException(message: String) : DomainException(message)