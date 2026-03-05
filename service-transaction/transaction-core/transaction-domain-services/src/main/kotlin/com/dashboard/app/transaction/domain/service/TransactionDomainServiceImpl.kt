package com.dashboard.app.transaction.domain.service

import com.dashboard.app.common.domain.model.valueobject.Money
import com.dashboard.app.transaction.domain.model.entity.Account
import com.dashboard.app.transaction.domain.model.entity.Card
import com.dashboard.app.transaction.domain.model.entity.Transaction
import com.dashboard.app.transaction.domain.model.entity.User
import com.dashboard.app.transaction.domain.model.event.TransactionCreatedEvent
import com.dashboard.app.transaction.domain.model.exception.TransactionDomainException
import io.github.oshai.kotlinlogging.KotlinLogging
import java.time.ZonedDateTime

private val logger = KotlinLogging.logger {}

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
        val transaction = Transaction.initialize(
            user.id,
            account?.id,
            card?.id,
            amount,
            effectiveAt
        )
        logger.info { "Transaction domain entity '${transaction.id}' created successfully for user ${user.id} with ${if(account!=null) "account: '${account.id}'" else ""} ${if(card!=null) "card: '${card.id}'" else ""}." }
        return TransactionCreatedEvent(transaction)
    }

    private fun validateUser(user: User) {
        if (!user.isActive()) {
            logger.warn { "Inactive user ${user.id} used for transaction initiation"  }
            throw TransactionDomainException("An inactive user cannot initiate transactions.")
        }
    }

    private fun validateAccount(account: Account?) {
        account?.let {
            if (!account.isActive()) {
                logger.warn { "Inactive account ${account.id} used for transaction initiation"  }
                throw TransactionDomainException("An inactive account cannot be used to initiate a transaction.")
            }
        }
    }

    private fun validateCard(card: Card?) {
        card?.let {
            if (!card.isActive()) {
                logger.warn { "Inactive card ${card.id} used for transaction initiation"  }
                throw TransactionDomainException("An inactive card cannot be used to initiate a transaction.")
            }
        }
    }

}