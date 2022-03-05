package io.aimc.reportingservice.dto.mapper

import io.aimc.reportingservice.dto.ReportDto
import io.aimc.reportingservice.model.Report
import org.springframework.stereotype.Component

@Component
class ReportMapper {
    fun toDto(report: Report) = ReportDto(
        report.portionAmount,
        report.letterAmount
    )
}
