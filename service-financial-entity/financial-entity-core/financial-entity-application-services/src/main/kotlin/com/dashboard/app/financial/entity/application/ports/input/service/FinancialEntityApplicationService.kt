package com.dashboard.app.financial.entity.application.ports.input.service

import com.dashboard.app.financial.entity.application.dto.findall.FindAllResponse

interface FinancialEntityApplicationService {

    fun findAll(): FindAllResponse

}