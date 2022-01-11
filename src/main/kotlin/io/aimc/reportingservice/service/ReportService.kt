package io.aimc.reportingservice.service

import io.aimc.reportingservice.model.Report
import java.time.LocalDate

interface ReportService {
    fun getReportByDate(date: LocalDate): Report
    fun getReportByWeek(fromDate: LocalDate): Report
}