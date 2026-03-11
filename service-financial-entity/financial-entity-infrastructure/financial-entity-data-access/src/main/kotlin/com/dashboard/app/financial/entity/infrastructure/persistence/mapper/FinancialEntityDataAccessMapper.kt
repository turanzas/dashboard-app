package com.dashboard.app.financial.entity.infrastructure.persistence.mapper

import com.dashboard.app.financial.entity.application.dto.findall.FinancialEntity
import com.dashboard.app.financial.entity.infrastructure.persistence.entity.FinancialEntityEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants.ComponentModel.SPRING

@Mapper(componentModel = SPRING)
interface FinancialEntityDataAccessMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    fun toEntity(financialEntity: FinancialEntity): FinancialEntityEntity

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    fun toApplication(financialEntityEntity: FinancialEntityEntity): FinancialEntity

}