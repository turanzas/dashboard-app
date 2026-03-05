package com.dashboard.app.financial.entity.interfaces.rest

import com.dashboard.app.financial.entity.application.dto.findall.FindAllFinancialEntityResponse
import com.dashboard.app.financial.entity.application.ports.input.service.FinancialEntityApplicationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/financial-entities")
class FinancialEntityController(
    private val applicationService: FinancialEntityApplicationService
) {

    @GetMapping(version = "v1+")
    fun findAll(): FindAllFinancialEntityResponse = applicationService.find()

}