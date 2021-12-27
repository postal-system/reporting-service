package io.aimc.reportingservice.service.impl

import io.aimc.reportingservice.entity.Portion
import io.aimc.reportingservice.model.Report
import io.aimc.reportingservice.repository.PortionRepository
import io.aimc.reportingservice.service.PortionService
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Service
class PortionServiceImpl(private val portionRepository: PortionRepository) : PortionService {

    override fun addPortion(portion: Portion) {
        portionRepository.save(portion)
    }

    override fun getReportByDate(date: LocalDate):Report {
        val portionAmount:Int = portionRepository.countPortionByDate(date)
        val shipmentAmount:Int = portionRepository.countShipmentByDate(date)
        return Report(portionAmount,shipmentAmount)
    }

//    override fun getReportByWeek(date: Instant):Report {
//        TODO("Not yet implemented")
//    }
}
