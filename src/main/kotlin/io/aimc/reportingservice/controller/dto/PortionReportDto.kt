package io.aimc.reportingservice.controller.dto

import java.time.LocalDate

data class PortionReportDto(
    val portionAmount: Long,
    val shipmentAmount: Long,
    val sendingDate: LocalDate
)
