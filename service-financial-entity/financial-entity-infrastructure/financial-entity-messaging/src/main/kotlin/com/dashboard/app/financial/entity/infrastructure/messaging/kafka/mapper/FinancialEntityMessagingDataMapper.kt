package com.dashboard.app.financial.entity.infrastructure.messaging.kafka.mapper

import com.dashboard.app.financial.entity.domain.model.event.FinancialEntityCreatedEvent
import com.dashboard.app.financial.entity.domain.model.event.FinancialEntityUpdatedEvent
import com.dashboard.app.kafka.account.avro.model.FinancialEntityCreatedAvroModel
import com.dashboard.app.kafka.account.avro.model.FinancialEntityUpdatedAvroModel
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants.ComponentModel.SPRING

@Mapper(componentModel = SPRING)
interface FinancialEntityMessagingDataMapper {

    @Mapping(target = "id", source = "createdEvent.financialEntity.id.value")
    fun toCreatedMessage(createdEvent: FinancialEntityCreatedEvent): FinancialEntityCreatedAvroModel

    @Mapping(target = "id", source = "updatedEvent.financialEntity.id.value")
    @Mapping(target = "status", source = "updatedEvent.financialEntity.status")
    fun toUpdatedMessage(updatedEvent: FinancialEntityUpdatedEvent): FinancialEntityUpdatedAvroModel

}