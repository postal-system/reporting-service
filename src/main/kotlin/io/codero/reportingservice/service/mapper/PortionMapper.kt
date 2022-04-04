package io.codero.reportingservice.service.mapper

import io.codero.reportingservice.controller.dto.PortionDto
import io.codero.reportingservice.domain.model.Portion
import org.springframework.stereotype.Component

@Component
class PortionMapper {
    fun fromDto(dto: PortionDto) = Portion(
        dto.id,
        dto.letterIds,
        dto.timestamp
    )
}
