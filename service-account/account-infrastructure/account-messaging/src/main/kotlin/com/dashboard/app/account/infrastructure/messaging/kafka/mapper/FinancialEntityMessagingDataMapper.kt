package com.dashboard.app.account.infrastructure.messaging.kafka.mapper

import com.dashboard.app.account.application.dto.message.FinancialEntityCreated
import com.dashboard.app.kafka.account.avro.model.FinancialEntityCreatedAvroModel
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants.ComponentModel.SPRING

@Mapper(componentModel = SPRING)
interface FinancialEntityMessagingDataMapper {

    @Mapping(target = "id", source = "id")
    fun toCreatedApplication(financialEntityCreatedAvroModel: FinancialEntityCreatedAvroModel): FinancialEntityCreated

}