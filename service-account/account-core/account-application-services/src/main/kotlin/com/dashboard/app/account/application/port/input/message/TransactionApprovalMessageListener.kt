package com.dashboard.app.account.application.port.input.message

import com.dashboard.app.account.application.dto.message.TransactionApprovalRequest

interface TransactionApprovalMessageListener {

    fun transactionApproval(message: TransactionApprovalRequest)

}