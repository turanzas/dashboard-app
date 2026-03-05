package com.dashboard.app.transaction.domain.service

import com.dashboard.app.common.domain.model.valueobject.Money
import com.dashboard.app.transaction.domain.model.entity.Account
import com.dashboard.app.transaction.domain.model.entity.Card
import com.dashboard.app.transaction.domain.model.entity.Transaction
import com.dashboard.app.transaction.domain.model.entity.User
import com.dashboard.app.transaction.domain.model.event.TransactionCreatedEvent
import com.dashboard.app.transaction.domain.model.exception.TransactionDomainException
import java.time.ZonedDateTime

class TransactionDomainServiceImpl(): TransactionDomainService {

    override fun validateAndInitiateTransaction(
        user: User,
        account: Account?,
        card: Card?,
        amount: Money,
        effectiveAt: ZonedDateTime
    ): TransactionCreatedEvent {
        // perform validations
        validateUser(user)
        validateAccount(account)
        validateCard(card)
        // create event with initialized transaction
        return TransactionCreatedEvent(
            Transaction.initialize(
                user.id,
                account?.id,
                card?.id,
                amount,
                effectiveAt
            )
        )
    }

    private fun validateCard(card: Card?) {
        card?.let {
            if (!card.isActive()) {
                throw TransactionDomainException("An inactive card cannot be used to initiate a transaction.")
            }
        }
    }

    private fun validateAccount(account: Account?) {
        account?.let {
            if (!account.isActive()) {
                throw TransactionDomainException("An inactive account cannot be used to initiate a transaction.")
            }
        }
    }

    private fun validateUser(user: User) {
        if (!user.isActive()) {
            throw TransactionDomainException("An inactive user cannot initiate transactions.")
        }
    }

}