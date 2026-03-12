package com.dashboard.app.financial.entity.config

import com.dashboard.app.financial.entity.domain.service.FinancialEntityDomainService
import com.dashboard.app.financial.entity.domain.service.FinancialEntityDomainServiceDefault
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan("com.dashboard.app.kafka")
class BeanConfiguration {

    @Bean
    fun financialEntityDomainService(): FinancialEntityDomainService {
        return FinancialEntityDomainServiceDefault()
    }

}