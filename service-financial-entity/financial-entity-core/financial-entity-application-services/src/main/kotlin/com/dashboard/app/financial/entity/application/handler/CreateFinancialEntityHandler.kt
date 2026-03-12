package com.dashboard.app.financial.entity.application.handler

import com.dashboard.app.financial.entity.application.dto.create.CreateFinancialEntityCommand
import com.dashboard.app.financial.entity.application.dto.create.CreateFinancialEntityResponse
import com.dashboard.app.financial.entity.application.helper.CreateFinancialEntityHelper
import com.dashboard.app.financial.entity.application.mapper.FinancialEntityDataMapper
import com.dashboard.app.financial.entity.application.ports.output.message.FinancialEntityCreatedMessagePublisher
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component

val log = KotlinLogging.logger {  }

@Component
class CreateFinancialEntityHandler(
    val createFinancialEntityHelper: CreateFinancialEntityHelper,
    val financialEntityCreatedMessagePublisher: FinancialEntityCreatedMessagePublisher,
    val financialEntityDataMapper: FinancialEntityDataMapper,
) {

    fun process(command: CreateFinancialEntityCommand): CreateFinancialEntityResponse {
        log.info { "Handling create financial entity command" }
        val financialEntityCreatedEvent = createFinancialEntityHelper.process(command)
        log.info { "Publishing financial entity created event for financial entity '${financialEntityCreatedEvent.financialEntity.id}'" }
        financialEntityCreatedMessagePublisher.publish(financialEntityCreatedEvent)
        return financialEntityDataMapper.toResponse(financialEntityCreatedEvent)
    }

}