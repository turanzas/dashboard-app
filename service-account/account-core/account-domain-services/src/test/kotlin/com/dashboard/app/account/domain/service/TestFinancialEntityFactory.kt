package com.dashboard.app.account.domain.service

import com.dashboard.app.account.domain.model.entity.FinancialEntity
import com.dashboard.app.common.domain.model.valueobject.FinancialEntityId
import com.dashboard.app.common.domain.model.valueobject.FinancialEntityStatus.ACTIVE
import com.dashboard.app.common.domain.model.valueobject.FinancialEntityStatus.INACTIVE
import java.util.*

class TestFinancialEntityFactory {

    companion object {

        fun createActiveFinancialEntity(): FinancialEntity =
            FinancialEntity(FinancialEntityId(UUID.randomUUID()), ACTIVE)

        fun createInactiveFinancialEntity(): FinancialEntity =
            FinancialEntity(FinancialEntityId(UUID.randomUUID()), INACTIVE)

    }

}