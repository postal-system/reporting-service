package io.aimc.reportingservice.domain.model

import java.time.LocalDate

data class PortionReport(
    var portionAmount: Long,
    var letterAmount: Long,
    var sendingDate: LocalDate
)
