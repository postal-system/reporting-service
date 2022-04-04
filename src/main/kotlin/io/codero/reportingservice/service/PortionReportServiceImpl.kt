package io.codero.reportingservice.service

import io.codero.reportingservice.domain.PortionReportService
import io.codero.reportingservice.domain.PortionRepository
import io.codero.reportingservice.domain.model.IReportPortion
import io.codero.reportingservice.domain.model.PortionReport
import java.time.LocalDate
import org.springframework.stereotype.Service

@Service
class PortionReportServiceImpl(
    private val portionRepository: PortionRepository,
) : PortionReportService {

    override fun getByDate(fromDate: LocalDate): List<PortionReport> {
        val report: List<IReportPortion> = portionRepository.getReportPortion(fromDate, fromDate.plusDays(1))
        return report.map {
            (PortionReport(
                it.getPortionAmount(),
                it.getLetterAmount(),
                it.getSendingDate().toLocalDate()
            ))
        }
    }

    override fun getByWeek(fromDate: LocalDate): List<PortionReport> {
        val start: LocalDate = fromDate.minusDays(6)
        val end: LocalDate = fromDate.plusDays(1)
        val report: List<IReportPortion> =
            portionRepository.getReportPortion(start, end)
        return report.map {
            PortionReport(
                it.getPortionAmount(),
                it.getLetterAmount(),
                it.getSendingDate().toLocalDate()
            )
        }
    }
}
