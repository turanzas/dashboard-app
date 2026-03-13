package com.dashboard.app.account.application.listener

import com.dashboard.app.account.application.dto.message.FinancialEntityCreated
import com.dashboard.app.account.application.port.input.message.FinancialEntityCreatedMessageListener
import org.springframework.stereotype.Service

@Service
class FinancialEntityCreatedMessageListenerDefault(
): FinancialEntityCreatedMessageListener {

    override fun process(financialEntity: FinancialEntityCreated) {
        TODO("Not implemented yet")
    }

}