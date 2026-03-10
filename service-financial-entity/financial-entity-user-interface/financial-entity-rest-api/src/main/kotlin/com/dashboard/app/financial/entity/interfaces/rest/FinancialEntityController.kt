package com.dashboard.app.financial.entity.interfaces.rest

import com.dashboard.app.financial.entity.application.dto.create.CreateFinancialEntityCommand
import com.dashboard.app.financial.entity.application.dto.create.CreateFinancialEntityResponse
import com.dashboard.app.financial.entity.application.dto.findall.FindAllFinancialEntityQuery
import com.dashboard.app.financial.entity.application.dto.findall.FindAllFinancialEntityResponse
import com.dashboard.app.financial.entity.application.ports.input.service.FinancialEntityApplicationService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

val log = KotlinLogging.logger { }

@RestController
@RequestMapping("/financial-entities")
class FinancialEntityController(
    private val applicationService: FinancialEntityApplicationService
) {

    @PostMapping
    fun createFinancialEntity(command: CreateFinancialEntityCommand): CreateFinancialEntityResponse {
        log.info { "Received request to create financial entity with name: ${command.name}" }
        val response = applicationService.createFinancialEntity(command)
        log.info { "Financial entity created with ID: ${response.financialEntityId}" }
        return response
    }

    @GetMapping(version = "v1+")
    fun findAll(): FindAllFinancialEntityResponse {
        log.info { "Received request to find all financial entities" }
        val response = applicationService.find(FindAllFinancialEntityQuery())
        log.info { "Found ${response.financialEntities.size} financial entities" }
        return response
    }

}