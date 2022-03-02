package io.aimc.reportingservice.facade

import io.aimc.reportingservice.dto.ReportDto
import io.aimc.reportingservice.dto.mapper.ReportMapper
import io.aimc.reportingservice.service.ReportService
import io.aimc.reportingservice.util.FileUtil
import java.time.LocalDate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ReportFacade(
    private val reportService: ReportService,
    private val reportMapper: ReportMapper,
    // TODO: 02.03.2022 вынести зависимость directory в утилитный класс
    @Value("\${reports.directory}")
    val directory: String
) {
    fun getReportByDate(date: LocalDate): ReportDto {
        val report = reportService.getReportByDate(date)
        FileUtil.toXml(directory, date, report);
        return reportMapper.toDto(report)
    }

    fun getReportByWeek(fromDate: LocalDate): ReportDto {
        val report = reportService.getReportByWeek(fromDate)
        FileUtil.toXml(directory, fromDate, report)
        return reportMapper.toDto(report)
    }
}
