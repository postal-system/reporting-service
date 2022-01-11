package io.aimc.reportingservice.service.impl

import io.aimc.reportingservice.entity.Portion
import io.aimc.reportingservice.model.Report
import io.aimc.reportingservice.repository.PortionRepository
import io.aimc.reportingservice.service.ReportService
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ReportServiceImpl(private val portionRepository: PortionRepository) : ReportService {

    override fun getReportByDate(date: LocalDate): Report {
        val portionAmount: Int = portionRepository.count(dateEqual(date)).toInt()
        val shipmentAmount: Int = portionRepository.countShipmentByDate(date)
        return Report(portionAmount, shipmentAmount)
    }

    override fun getReportByWeek(fromDate: LocalDate): Report {
        val portionAmount: Int = portionRepository.countPortionByWeek(fromDate)
        val shipmentAmount: Int = portionRepository.countShipmentByWeek(fromDate)
        return Report(portionAmount, shipmentAmount)
    }

    private fun dateEqual(date: LocalDate): Specification<Portion> {
        return Specification<Portion> { root, query, criteriaBuilder ->
            criteriaBuilder.equal(root.get<LocalDate>("date"), date)
        }
    }

}