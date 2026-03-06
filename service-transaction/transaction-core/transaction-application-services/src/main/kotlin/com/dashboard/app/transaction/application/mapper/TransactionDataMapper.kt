package com.dashboard.app.transaction.application.mapper

import com.dashboard.app.transaction.application.dto.create.CreateTransactionResponse
import com.dashboard.app.transaction.application.dto.model.Transaction
import com.dashboard.app.transaction.domain.model.event.TransactionCreatedEvent
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants.ComponentModel.SPRING

/**
 * Mapper interface for transaction data transformations.
 */
@Mapper(componentModel = SPRING)
interface TransactionDataMapper {

    @Mapping(target = "transactionId", source = "event.transaction.id.value")
    @Mapping(target = "userId", source = "event.transaction.userId.value")
    @Mapping(target = "accountId", source = "event.transaction.accountId.value")
    @Mapping(target = "cardId", source = "event.transaction.cardId.value")
    @Mapping(target = "amount", source = "event.transaction.amount.value")
    @Mapping(target = "status", source = "event.transaction.status")
    @Mapping(target = "effectiveAt", source = "event.transaction.effectiveAt")
    fun toTransaction(event: TransactionCreatedEvent): Transaction

    @Mapping(target = "status", source = "event.transaction.status")
    fun toCreateTransactionResponse(event: TransactionCreatedEvent): CreateTransactionResponse

}