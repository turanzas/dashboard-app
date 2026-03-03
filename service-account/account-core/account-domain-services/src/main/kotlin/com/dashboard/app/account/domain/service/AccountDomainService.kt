package com.dashboard.app.account.domain.service

import com.dashboard.app.account.domain.model.entity.Account
import com.dashboard.app.account.domain.model.entity.FinancialEntity
import com.dashboard.app.account.domain.model.entity.User
import com.dashboard.app.account.domain.model.event.AccountCreatedEvent
import com.dashboard.app.account.domain.model.event.AccountStatusChangedEvent

/**
 * Account domain service handles business logic related to account domain.
 */
interface AccountDomainService {

    /**
     * Validates the financial entity and user, then initiates the account creation process.
     *
     * @param financialEntity The financial entity for which the account is being created.
     * @param user The user for whom the account is being created.
     * @return An event indicating that the account has been created.
     * @throws com.dashboard.app.account.domain.model.exception.AccountDomainException if the financial entity or user is inactive.
     */
    fun validateAndInitiateAccount(financialEntity: FinancialEntity, user: User): AccountCreatedEvent

    fun activate(account: Account): AccountStatusChangedEvent

    fun deactivate(account: Account): AccountStatusChangedEvent

    fun close(account: Account): AccountStatusChangedEvent

}