package com.dashboard.app.account.application.listener

import com.dashboard.app.account.application.dto.message.TransactionApprovalRequest
import com.dashboard.app.account.application.port.input.message.TransactionApprovalMessageListener
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated

@Service
@Validated
class TransactionApprovalMessageListenerImpl: TransactionApprovalMessageListener {

    override fun transactionApproval(message: TransactionApprovalRequest) {
        TODO("Not yet implemented")
    }

}