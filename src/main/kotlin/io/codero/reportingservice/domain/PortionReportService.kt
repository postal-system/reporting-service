package io.codero.reportingservice.domain

import io.codero.reportingservice.domain.model.PortionReport
import java.time.LocalDate

interface PortionReportService {
    fun getByDate(fromDate: LocalDate):  List<PortionReport>
    fun getByWeek(fromDate: LocalDate):  List<PortionReport>
}