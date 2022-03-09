package io.aimc.reportingservice.service.impl

import io.aimc.reportingservice.entity.Portion
import io.aimc.reportingservice.repository.PortionRepository
import io.aimc.reportingservice.service.PortionService
import java.time.LocalDate
import org.springframework.stereotype.Service

@Service
class PortionServiceImpl(private val portionRepository: PortionRepository) : PortionService {
    override fun addRawPortion(portion: Portion) {
        portionRepository.save(portion)
    }

    override fun getLetterIdsByTimes(fromDate: LocalDate): List<Portion> {
        TODO("Not yet implemented")
    }

//    override fun getLetterIdsByTimes(fromDate: LocalDate): List<Portion> {
//        val start = LocalDateTime.of(fromDate, LocalTime.of(0, 0)).minusWeeks(1)
//        val end = LocalDateTime.of(fromDate, LocalTime.of(0, 0))
//        return portionRepository.getLetterIdsByTimes(start, end)
//    }
}
