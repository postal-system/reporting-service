package io.aimc.reportingservice.facade

import io.aimc.reportingservice.dto.PortionDto
import io.aimc.reportingservice.dto.mapper.PortionMapper
import io.aimc.reportingservice.service.PortionService
import org.springframework.stereotype.Component

@Component
class PortionFacade(
    private val portionService: PortionService,
    private val portionMapper: PortionMapper
) {
    fun addPortion(portionDto: PortionDto) {
        portionService.addPortion(portionMapper.fromDto(portionDto))
    }
}
