package com.dashboard.app.account.application.dto.create

import java.util.*

class CreateAccountCommand (
    val userId: UUID,
    val financialEntityId: UUID,
    val accountName: String,
    val initialBalance: Double,
)