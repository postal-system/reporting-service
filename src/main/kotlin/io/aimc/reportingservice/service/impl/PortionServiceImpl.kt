package io.aimc.reportingservice.service.impl

import io.aimc.reportingservice.entity.Portion
import io.aimc.reportingservice.model.Report
import io.aimc.reportingservice.repository.PortionRepository
import io.aimc.reportingservice.service.PortionService
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class PortionServiceImpl(private val portionRepository: PortionRepository) : PortionService {

    override fun addPortion(portion: Portion) {
        portionRepository.save(portion)
    }

    override fun getReportByDate(date: LocalDate): Report {
        val portionAmount: Int = portionRepository.count(dateEqual(date)).toInt()
        val shipmentAmount: Int = portionRepository.countShipmentByDate(date) //todo write specification
        return Report(portionAmount, shipmentAmount)
    }

    override fun getReportByWeek(fromDate: LocalDate): Report {
        val portionAmount: Int = portionRepository.countPortionByWeek(fromDate) //todo write specification
        val shipmentAmount: Int = portionRepository.countShipmentByWeek(fromDate) //todo write specification
        return Report(portionAmount, shipmentAmount)
    }

    fun dateEqual(date: LocalDate): Specification<Portion> {
        return Specification<Portion> { root, query, builder ->
            builder.equal(root.get<LocalDate>("date"), date)
        }
    }
}
