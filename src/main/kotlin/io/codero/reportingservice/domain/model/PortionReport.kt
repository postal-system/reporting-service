package io.codero.reportingservice.domain.model

import java.time.LocalDate

data class PortionReport(
    val portionAmount: Long,
    val letterAmount: Long,
    val sendingDate: LocalDate
)
