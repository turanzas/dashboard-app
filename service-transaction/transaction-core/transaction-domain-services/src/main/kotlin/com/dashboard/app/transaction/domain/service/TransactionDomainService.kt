package com.dashboard.app.transaction.domain.service

import com.dashboard.app.common.domain.model.valueobject.Money
import com.dashboard.app.transaction.domain.model.entity.Account
import com.dashboard.app.transaction.domain.model.entity.Card
import com.dashboard.app.transaction.domain.model.entity.User
import com.dashboard.app.transaction.domain.model.event.TransactionCreatedEvent
import java.time.ZonedDateTime

/**
 * TransactionDomainService defines the core business logic for validating and initiating transactions.
 * It ensures that all necessary checks are performed before a transaction is created.
 */
interface TransactionDomainService {

    /**
     * Validates the transaction details and initiates the transaction if all checks pass.
     * Only one of the account or card parameters should be provided, not both.
     * If neither is provided, an exception will be thrown.
     *
     * @param user The user initiating the transaction.
     * @param account The account from which the transaction will be made (optional).
     * @param card The card used for the transaction (optional).
     * @param amount The amount of money to be transacted.
     * @param effectiveAt The date and time when the transaction should take effect.
     * @return A TransactionCreatedEvent containing details of the created transaction.
     * @throws IllegalArgumentException if validation fails for any of the parameters.
     */
    fun validateAndInitiateTransaction(
        user: User,
        account: Account? = null,
        card: Card? = null,
        amount: Money,
        effectiveAt: ZonedDateTime
    ): TransactionCreatedEvent

}