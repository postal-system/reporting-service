package io.aimc.reportingservice.service.impl

import io.aimc.reportingservice.entity.Portion
import io.aimc.reportingservice.repository.PortionRepository
import io.aimc.reportingservice.service.PortionService
import org.springframework.stereotype.Service

@Service
class PortionServiceImpl(private val portionRepository: PortionRepository) : PortionService {
    override fun addPortion(portion: Portion) {
        portionRepository.save(portion)
    }
}
