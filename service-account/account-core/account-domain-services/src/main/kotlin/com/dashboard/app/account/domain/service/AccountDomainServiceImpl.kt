package com.dashboard.app.account.domain.service

import com.dashboard.app.account.domain.model.entity.Account
import com.dashboard.app.account.domain.model.entity.FinancialEntity
import com.dashboard.app.account.domain.model.entity.User
import com.dashboard.app.account.domain.model.event.AccountCreatedEvent
import com.dashboard.app.account.domain.model.exception.AccountDomainException

class AccountDomainServiceImpl: AccountDomainService {

    override fun validateAndInitiateAccount(
        financialEntity: FinancialEntity,
        user: User
    ): AccountCreatedEvent {
        validateFinancialEntity(financialEntity)
        validateUser(user)
        return AccountCreatedEvent(Account.initialize(financialEntity.id, user.id))
    }

    private fun validateFinancialEntity(financialEntity: FinancialEntity) {
        if (!financialEntity.isActive()) {
            throw AccountDomainException("Cannot create account for an inactive financial entity.")
        }
    }

    private fun validateUser(user: User) {
        if (!user.isActive()) {
            throw AccountDomainException("Cannot create account for an inactive user.")
        }
    }

}