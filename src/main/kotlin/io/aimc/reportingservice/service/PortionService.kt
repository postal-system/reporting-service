package io.aimc.reportingservice.service

import io.aimc.reportingservice.entity.Portion
import io.aimc.reportingservice.model.Report
import java.time.Instant

interface PortionService {
    fun addPortion(portion: Portion)
    fun getReportByDate(date: Instant):Report
    fun getReportByWeek(date: Instant):Report
}
