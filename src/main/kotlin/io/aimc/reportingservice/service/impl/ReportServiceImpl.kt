package io.aimc.reportingservice.service.impl

import io.aimc.reportingservice.model.Report
import io.aimc.reportingservice.repository.PortionRepository
import io.aimc.reportingservice.service.ReportService
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import org.springframework.stereotype.Service

@Service
class ReportServiceImpl(
    private val portionRepository: PortionRepository,
) : ReportService {
    private fun getReportByBetweenTime(start: LocalDateTime, end: LocalDateTime): Report {
        val portionAmount: Long = portionRepository.getCountPortionByBetweenTime(start, end)
        val letterAmount: Long = portionRepository.getCountLetterByBetweenTime(start, end)
        return Report(portionAmount, letterAmount)
    }

    override fun getReportByDate(date: LocalDate): Report {
        val start = LocalDateTime.of(date, LocalTime.of(0, 0))
        val end = LocalDateTime.of(date, LocalTime.of(0, 0)).plusDays(1)
        return getReportByBetweenTime(start, end)
    }

    override fun getReportByWeek(fromDate: LocalDate): Report {
        val start = LocalDateTime.of(fromDate, LocalTime.of(0, 0)).minusWeeks(1)
        val end = LocalDateTime.of(fromDate, LocalTime.of(0, 0))
        return getReportByBetweenTime(start, end)
    }
}