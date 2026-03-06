package com.dashboard.app.transaction.application.ports.output.repository

import com.dashboard.app.transaction.domain.model.entity.Account
import java.util.UUID

interface AccountRepository {

    fun findById(accountId: UUID): Account?

}