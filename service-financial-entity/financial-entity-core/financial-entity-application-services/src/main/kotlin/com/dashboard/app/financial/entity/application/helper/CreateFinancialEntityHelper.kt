package com.dashboard.app.financial.entity.application.helper

import com.dashboard.app.financial.entity.application.dto.create.CreateFinancialEntityCommand
import com.dashboard.app.financial.entity.application.mapper.FinancialEntityDataMapper
import com.dashboard.app.financial.entity.application.ports.output.repository.FinancialEntityRepository
import com.dashboard.app.financial.entity.domain.model.event.FinancialEntityCreatedEvent
import com.dashboard.app.financial.entity.domain.service.FinancialEntityDomainService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

val log = KotlinLogging.logger {}

@Component
class CreateFinancialEntityHelper(
    val financialEntityDomainService: FinancialEntityDomainService,
    val financialEntityRepository: FinancialEntityRepository,
    val financialEntityDataMapper: FinancialEntityDataMapper,
) {

    @Transactional
    fun process(command: CreateFinancialEntityCommand): FinancialEntityCreatedEvent {
        log.info { "Helping create financial entity request" }
        // perform domain logic
        val createdEvent = financialEntityDomainService.validateAndInitializeFinancialEntity(
            command.name
        )
        // map domain event to financial entity
        val financialEntity = financialEntityDataMapper.toApplication(createdEvent)
        // persist financial entity
        financialEntityRepository.save(financialEntity)
        // return event for further processing
        return createdEvent
    }

}