package com.dashboard.app.account.domain.service

import com.dashboard.app.account.domain.model.entity.FinancialEntity
import com.dashboard.app.common.domain.model.valueobject.FinancialEntityId
import com.dashboard.app.common.domain.model.valueobject.FinancialEntityStatus.ACTIVE
import com.dashboard.app.common.domain.model.valueobject.FinancialEntityStatus.INACTIVE

class TestFinancialEntityFactory {

    companion object {

        fun createActiveFinancialEntity(): FinancialEntity =
            FinancialEntity(FinancialEntityId.random(), ACTIVE)

        fun createInactiveFinancialEntity(): FinancialEntity =
            FinancialEntity(FinancialEntityId.random(), INACTIVE)

    }

}