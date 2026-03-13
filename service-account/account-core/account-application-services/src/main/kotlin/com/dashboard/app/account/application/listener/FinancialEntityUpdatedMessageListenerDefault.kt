package com.dashboard.app.account.application.listener

import com.dashboard.app.account.application.dto.message.FinancialEntityUpdated
import com.dashboard.app.account.application.port.input.message.FinancialEntityUpdatedMessageListener
import org.springframework.stereotype.Service

@Service
class FinancialEntityUpdatedMessageListenerDefault(
): FinancialEntityUpdatedMessageListener {

    override fun process(financialEntity: FinancialEntityUpdated) {
        TODO("Not yet implemented")
    }

}