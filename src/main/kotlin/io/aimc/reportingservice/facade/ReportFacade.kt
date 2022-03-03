package io.aimc.reportingservice.facade

import io.aimc.reportingservice.service.ReportService
import io.aimc.reportingservice.util.FileUtil
import java.time.LocalDate
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component

@Component
class ReportFacade(
    private val reportService: ReportService
) {
    fun getReportByDate(date: LocalDate): Resource {
        val report = reportService.getReportByDate(date)
        return FileUtil.toXml(report)
    }

    fun getReportByWeek(fromDate: LocalDate): Resource {
        val report = reportService.getReportByWeek(fromDate)
        return FileUtil.toXml(report)
    }
}
