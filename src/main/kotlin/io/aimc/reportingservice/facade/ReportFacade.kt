package io.aimc.reportingservice.facade

import io.aimc.reportingservice.dto.ReportDto
import io.aimc.reportingservice.dto.mapper.ReportMapper
import io.aimc.reportingservice.service.ReportService
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class ReportFacade(
    private val reportService: ReportService,
    private val reportMapper: ReportMapper
) {

    fun getReportByDate(date: LocalDate): ReportDto {
        return reportMapper.toDto(reportService.getReportByDate(date))
    }

    fun getReportByWeek(fromDate: LocalDate): ReportDto {
        return reportMapper.toDto(reportService.getReportByWeek(fromDate))
    }
}