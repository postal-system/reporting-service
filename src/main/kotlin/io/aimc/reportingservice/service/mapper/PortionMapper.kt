package io.aimc.reportingservice.service.mapper

import io.aimc.reportingservice.controller.dto.PortionDto
import io.aimc.reportingservice.domain.model.Portion
import org.springframework.stereotype.Component

@Component
class PortionMapper {
    fun fromDto(dto: PortionDto) = Portion(
        dto.id,
        dto.letterIds,
        dto.localDateTime
    )
}
