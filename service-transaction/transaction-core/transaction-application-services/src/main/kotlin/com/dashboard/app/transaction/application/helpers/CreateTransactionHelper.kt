package com.dashboard.app.transaction.application.helpers

import com.dashboard.app.common.domain.model.valueobject.Money
import com.dashboard.app.transaction.application.dto.create.CreateTransactionCommand
import com.dashboard.app.transaction.application.dto.model.Transaction
import com.dashboard.app.transaction.application.mapper.TransactionDataMapper
import com.dashboard.app.transaction.application.ports.output.repository.AccountRepository
import com.dashboard.app.transaction.application.ports.output.repository.CardRepository
import com.dashboard.app.transaction.application.ports.output.repository.TransactionRepository
import com.dashboard.app.transaction.application.ports.output.repository.UserRepository
import com.dashboard.app.transaction.domain.model.entity.Account
import com.dashboard.app.transaction.domain.model.entity.Card
import com.dashboard.app.transaction.domain.model.entity.User
import com.dashboard.app.transaction.domain.model.event.TransactionCreatedEvent
import com.dashboard.app.transaction.domain.model.exception.TransactionDomainException
import com.dashboard.app.transaction.domain.service.TransactionDomainService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime
import java.util.*

private val logger = KotlinLogging.logger {}

@Component
class CreateTransactionHelper(
    val transactionDomainService: TransactionDomainService,
    val transactionRepository: TransactionRepository,
    val accountRepository: AccountRepository,
    val cardRepository: CardRepository,
    val userRepository: UserRepository,
    val transactionDataMapper: TransactionDataMapper,
) {

    @Transactional
    fun process(command: CreateTransactionCommand): TransactionCreatedEvent {
        logger.info { "Helping create transaction request" }
        // perform validations
        val user = checkUser(command.userId)
        val account = checkAccount(command.accountId)
        val card = checkCard(command.cardId)
        // perform domain logic
        val transactionCreatedEvent = transactionDomainService.validateAndInitiateTransaction(
            user,
            account,
            card,
            Money(command.amount),
            ZonedDateTime.now()
        )
        // map domain event to transaction entity
        val transaction = transactionDataMapper.toTransaction(transactionCreatedEvent)
        // persist transaction
        saveTransaction(transaction)
        // return event for further processing
        return transactionCreatedEvent
    }

    private fun checkUser(userId: UUID): User =
        userRepository.findById(userId) ?: run {
                logger.warn { "Could not find user with id $userId" }
                throw TransactionDomainException("Could not find user with id $userId")
            }

    private fun checkAccount(accountId: UUID?): Account? =
        accountId?.let {
            accountRepository.findById(it) ?: run {
                logger.warn { "Could not find account with id $accountId" }
                throw TransactionDomainException("Could not find account with id $accountId")
            }
        }

    private fun checkCard(cardId: UUID?): Card? =
        cardId?.let {
            cardRepository.findById(it) ?: run {
                logger.warn { "Could not find card with id $cardId" }
                throw TransactionDomainException("Could not find card with id $cardId")
            }
        }

    private fun saveTransaction(transaction: Transaction) {
        transactionRepository.save(transaction) ?: run {
            logger.error { "Failed to save transaction with id ${transaction.transactionId}" }
            throw TransactionDomainException("Failed to save transaction with id ${transaction.transactionId}")
        }
        logger.info { "Transaction with id ${transaction.transactionId} saved successfully." }
    }

}