package com.dashboard.app.financial.entity.application.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "financial-entity-application-service")
class FinancialEntityApplicationServiceConfigData(
    var financialEntityCreatedTopicName: String = "",
    var financialEntityStatusUpdatedTopicName: String = "",
)