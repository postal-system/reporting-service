package io.aimc.reportingservice.service.mapper

import io.aimc.reportingservice.controller.dto.PortionReportDto
import io.aimc.reportingservice.domain.model.PortionReport
import org.springframework.stereotype.Component

@Component
class PortionReportMapper {
    fun toDto(portionReport: PortionReport) = PortionReportDto(
        portionReport.portionAmount,
        portionReport.letterAmount,
        portionReport.sendingDate
    )
}
