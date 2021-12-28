package io.aimc.reportingservice.service

import io.aimc.reportingservice.entity.Portion
import io.aimc.reportingservice.model.Report
import java.time.LocalDate

interface PortionService {
    fun addPortion(portion: Portion)
    fun getReportByDate(date: LocalDate):Report
    fun getReportByWeek(fromDate: LocalDate):Report
}
