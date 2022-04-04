package io.codero.reportingservice.service.mapper

import io.codero.reportingservice.controller.dto.PortionReportDto
import io.codero.reportingservice.domain.model.PortionReport
import org.springframework.stereotype.Component

@Component
class PortionReportMapper {
    fun toDto(portionReport: PortionReport) = PortionReportDto(
        portionReport.portionAmount,
        portionReport.letterAmount,
        portionReport.sendingDate
    )
}
