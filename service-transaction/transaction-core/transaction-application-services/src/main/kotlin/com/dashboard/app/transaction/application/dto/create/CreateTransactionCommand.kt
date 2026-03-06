package com.dashboard.app.transaction.application.dto.create

import java.math.BigDecimal
import java.util.*

class CreateTransactionCommand(
    val userId: UUID,
    val accountId: UUID? = null,
    val cardId: UUID? = null,
    val amount: BigDecimal,
)