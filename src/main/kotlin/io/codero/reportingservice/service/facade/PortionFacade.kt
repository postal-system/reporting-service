package io.codero.reportingservice.service.facade

import io.codero.reportingservice.controller.dto.PortionDto
import io.codero.reportingservice.domain.PortionService
import io.codero.reportingservice.service.mapper.PortionMapper
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
