package com.dashboard.app.account.domain.service

import com.dashboard.app.account.domain.model.entity.Account
import com.dashboard.app.account.domain.model.entity.FinancialEntity
import com.dashboard.app.account.domain.model.entity.User
import com.dashboard.app.account.domain.model.event.AccountCreatedEvent
import com.dashboard.app.account.domain.model.event.AccountStatusChangedEvent
import com.dashboard.app.account.domain.model.exception.AccountDomainException
import com.dashboard.app.common.domain.model.valueobject.AccountStatus.*
import io.github.oshai.kotlinlogging.KotlinLogging
import lombok.extern.slf4j.Slf4j

private val logger = KotlinLogging.logger {}

@Slf4j
class AccountDomainServiceImpl: AccountDomainService {

    override fun validateAndInitiateAccount(
        financialEntity: FinancialEntity,
        user: User
    ): AccountCreatedEvent {
        validateFinancialEntity(financialEntity)
        validateUser(user)
        val account = Account.initialize(financialEntity.id, user.id)
        logger.info { "Account domain entity '${account.id}' created successfully for user ${user.id} under financial entity ${financialEntity.id}." }
        return AccountCreatedEvent(account)
    }

    private fun validateFinancialEntity(financialEntity: FinancialEntity) {
        if (!financialEntity.isActive()) {
            logger.error { "Inactive financial entity ${financialEntity.id} used for account creation"  }
            throw AccountDomainException("Cannot create account for an inactive financial entity.")
        }
    }

    private fun validateUser(user: User) {
        if (!user.isActive()) {
            logger.error { "Inactive user ${user.id} used for account creation"  }
            throw AccountDomainException("Cannot create account for an inactive user.")
        }
    }

    override fun activate(account: Account): AccountStatusChangedEvent {
        val updated = account.activate()
        logger.info { "Account domain entity '${account.id}' has been activated '${updated}'" }
        return AccountStatusChangedEvent(account.id, ACTIVE, updated)
    }

    override fun deactivate(account: Account): AccountStatusChangedEvent {
        val updated = account.deactivate()
        logger.info { "Account domain entity '${account.id}' has been deactivated '${updated}'" }
        return AccountStatusChangedEvent(account.id, INACTIVE, updated)
    }

    override fun close(account: Account): AccountStatusChangedEvent {
        val updated = account.close()
        logger.info { "Account domain entity '${account.id}' has been closed '${updated}'" }
        return AccountStatusChangedEvent(account.id, CLOSED, updated)
    }

}