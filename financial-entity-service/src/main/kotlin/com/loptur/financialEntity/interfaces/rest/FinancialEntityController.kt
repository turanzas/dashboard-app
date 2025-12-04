package com.loptur.financialEntity.interfaces.rest

import com.loptur.financialEntity.domain.FinancialEntity
import com.loptur.financialEntity.domain.FinancialEntityRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/financial-entities")
final class FinancialEntityController(
    private val financialEntityRepository: FinancialEntityRepository
) {

    @GetMapping
    fun findAll(): List<FinancialEntity> = financialEntityRepository.findAll()

}