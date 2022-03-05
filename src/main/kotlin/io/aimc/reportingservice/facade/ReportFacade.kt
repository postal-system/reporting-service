package io.aimc.reportingservice.facade

import io.aimc.reportingservice.service.ReportService
import io.aimc.reportingservice.util.ReportFileService
import java.time.LocalDate
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component

@Component
class ReportFacade(
    private val reportService: ReportService,
    private val reportFileService: ReportFileService
) {
    fun getReportByDate(fromDate: LocalDate): Resource {
        val report = reportService.getReportByWeek(fromDate)
        return reportFileService.toXml(report)
    }

    fun getReportByWeek(fromDate: LocalDate): Resource {
        val report = reportService.getReportByWeek(fromDate)
        return reportFileService.toXml(report)
    }

    fun getReportByDate(date: LocalDate, type: String): Resource {
        val report = reportService.getReportByDate(date)
        return when (type) {
            "xml" -> reportFileService.toXml(report)
            "csv" -> reportFileService.toCsv(report)
            "xlsx" -> reportFileService.toXlsx(report)
            else -> throw Exception("Was choose wrong type")
        }
    }
}
