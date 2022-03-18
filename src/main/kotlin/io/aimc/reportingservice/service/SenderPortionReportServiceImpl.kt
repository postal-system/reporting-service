package io.aimc.reportingservice.service

import io.aimc.reportingservice.domain.PortionReportService
import io.aimc.reportingservice.domain.PortionRepository
import io.aimc.reportingservice.domain.model.IReportPortion
import io.aimc.reportingservice.domain.model.PortionReport
import java.time.LocalDate
import org.springframework.stereotype.Service

@Service
class SenderPortionReportServiceImpl(
    private val portionRepository: PortionRepository,
) : PortionReportService {

    override fun getByDate(fromDate: LocalDate): List<PortionReport> {
        val report: List<IReportPortion> = portionRepository.getReportPortion(fromDate, fromDate.plusDays(1))
        return report.map { PortionReport(it.getPortionAmount(), it.getLetterAmount(), it.getSendingDate().toLocalDate()) }.toList()
    }

    override fun getByWeek(fromDate: LocalDate): List<PortionReport> {
        val report: List<IReportPortion> = portionRepository.getReportPortion(fromDate.minusWeeks(1), fromDate)
        return report.map { PortionReport(it.getPortionAmount(), it.getLetterAmount(), it.getSendingDate().toLocalDate()) }
            .toList()
    }
}