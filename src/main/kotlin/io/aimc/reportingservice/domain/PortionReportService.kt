package io.aimc.reportingservice.domain

import io.aimc.reportingservice.domain.model.PortionReport
import java.time.LocalDate

interface PortionReportService {
    fun getByDate(fromDate: LocalDate):  List<PortionReport>
    fun getByWeek(fromDate: LocalDate):  List<PortionReport>
}