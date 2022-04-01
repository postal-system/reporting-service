package io.aimc.reportingservice.service

import io.aimc.reportingservice.domain.PortionRepository
import io.aimc.reportingservice.domain.PortionService
import io.aimc.reportingservice.domain.model.Portion
import java.time.LocalDate
import org.springframework.stereotype.Service

@Service
class PortionServiceImpl(private val portionRepository: PortionRepository) : PortionService {
    override fun addRawPortion(portion: Portion) {
        portionRepository.save(portion)
    }

    override fun getPortionsByDate(fromDate: LocalDate): List<Portion> {
        val start: LocalDate = fromDate
        val end: LocalDate = fromDate.plusDays(1)
        return portionRepository.getByTimes(start, end)
    }

    override fun getPortionsByLastWeek(fromDate: LocalDate): List<Portion> {
        val start: LocalDate = fromDate.minusDays(6)
        val end: LocalDate = fromDate.plusDays(1)
        return portionRepository.getByTimes(start, end)
    }
}
