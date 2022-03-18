package io.aimc.reportingservice.service.facade

import io.aimc.reportingservice.controller.dto.PortionDto
import io.aimc.reportingservice.domain.PortionService
import io.aimc.reportingservice.service.mapper.PortionMapper
import org.springframework.stereotype.Component

@Component
class PortionFacade(
    private val portionService: PortionService,
    private val portionMapper: PortionMapper
) {
    fun addPortion(portionDto: PortionDto) {
        val portion = portionMapper.fromDto(portionDto);
        portionService.addRawPortion(portion)
    }
}
