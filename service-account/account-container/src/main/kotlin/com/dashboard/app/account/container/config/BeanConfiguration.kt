package com.dashboard.app.account.container.config

import com.dashboard.app.account.domain.service.AccountDomainService
import com.dashboard.app.account.domain.service.AccountDomainServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan("com.dashboard.app.kafka")
class BeanConfiguration {

    @Bean
    fun accountDomainService(): AccountDomainService {
        return AccountDomainServiceImpl()
    }

}