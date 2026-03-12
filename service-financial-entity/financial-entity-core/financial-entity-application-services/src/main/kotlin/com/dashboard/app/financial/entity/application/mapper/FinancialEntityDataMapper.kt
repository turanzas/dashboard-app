package com.dashboard.app.financial.entity.application.mapper

import com.dashboard.app.financial.entity.application.dto.create.CreateFinancialEntityResponse
import com.dashboard.app.financial.entity.application.dto.findall.FinancialEntity
import com.dashboard.app.financial.entity.domain.model.event.FinancialEntityCreatedEvent
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants.ComponentModel.SPRING

@Mapper(componentModel = SPRING)
interface FinancialEntityDataMapper {

    @Mapping(target = "id", source = "createdEvent.financialEntity.id.value")
    @Mapping(target = "name", source = "createdEvent.financialEntity.name")
    fun toApplication(createdEvent: FinancialEntityCreatedEvent): FinancialEntity

    @Mapping(target = "financialEntityId", source = "financialEntityCreatedEvent.financialEntity.id.value")
    fun toResponse(financialEntityCreatedEvent: FinancialEntityCreatedEvent): CreateFinancialEntityResponse

}