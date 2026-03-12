package com.dashboard.app.financial.entity.infrastructure.messaging.kafka.mapper

import com.dashboard.app.financial.entity.domain.model.event.FinancialEntityCreatedEvent
import com.dashboard.app.kafka.account.avro.model.FinancialEntityCreatedAvroModel
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants.ComponentModel.SPRING

@Mapper(componentModel = SPRING)
interface FinancialEntityMessagingDataMapper {

    @Mapping(target = "id", source = "createdEvent.financialEntity.id.value")
    fun toMessage(createdEvent: FinancialEntityCreatedEvent): FinancialEntityCreatedAvroModel

}