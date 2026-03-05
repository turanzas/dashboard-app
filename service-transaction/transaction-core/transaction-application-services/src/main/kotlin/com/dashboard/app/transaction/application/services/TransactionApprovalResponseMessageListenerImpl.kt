package com.dashboard.app.transaction.application.services

import com.dashboard.app.transaction.application.dto.message.TransactionApprovalResponse
import com.dashboard.app.transaction.application.ports.input.message.TransactionApprovalResponseMessageListener

class TransactionApprovalResponseMessageListenerImpl: TransactionApprovalResponseMessageListener {

    override fun process(message: TransactionApprovalResponse) {
        TODO("Not yet implemented")
    }

}