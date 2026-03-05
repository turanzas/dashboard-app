package com.dashboard.app.financial.entity.application.services

import com.dashboard.app.financial.entity.application.dto.create.CreateFinancialEntityCommand
import com.dashboard.app.financial.entity.application.dto.create.CreateFinancialEntityResponse
import com.dashboard.app.financial.entity.application.dto.findall.FindAllFinancialEntityQuery
import com.dashboard.app.financial.entity.application.dto.findall.FindAllFinancialEntityResponse
import com.dashboard.app.financial.entity.application.ports.input.service.FinancialEntityApplicationService
import jakarta.validation.Valid
import org.springframework.stereotype.Service

@Service
class FinancialEntityApplicationService: FinancialEntityApplicationService {

    override fun createFinancialEntity(@Valid command: CreateFinancialEntityCommand): CreateFinancialEntityResponse {
        TODO("Not yet implemented")
    }

    override fun find(query: FindAllFinancialEntityQuery): FindAllFinancialEntityResponse {
        TODO("Not yet implemented")
    }

}