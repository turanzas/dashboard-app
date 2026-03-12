package com.dashboard.app.financial.entity.application.services

import com.dashboard.app.financial.entity.application.dto.create.CreateFinancialEntityCommand
import com.dashboard.app.financial.entity.application.dto.create.CreateFinancialEntityResponse
import com.dashboard.app.financial.entity.application.dto.findall.FindAllFinancialEntityQuery
import com.dashboard.app.financial.entity.application.dto.findall.FindAllFinancialEntityResponse
import com.dashboard.app.financial.entity.application.handler.CreateFinancialEntityHandler
import com.dashboard.app.financial.entity.application.ports.input.service.FinancialEntityApplicationService
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.validation.Valid
import org.springframework.stereotype.Service

val log = KotlinLogging.logger {}

@Service
class FinancialEntityApplicationService(
    val createFinancialEntityHandler: CreateFinancialEntityHandler
): FinancialEntityApplicationService {

    override fun createFinancialEntity(@Valid command: CreateFinancialEntityCommand): CreateFinancialEntityResponse {
        log.info { "Received create financial entity command: $command" }
        return createFinancialEntityHandler.process(command)
    }

    override fun find(query: FindAllFinancialEntityQuery): FindAllFinancialEntityResponse {
        TODO("Not yet implemented")
    }

}