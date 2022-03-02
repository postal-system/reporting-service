package io.aimc.reportingservice.dto.mapper

import io.aimc.reportingservice.dto.PortionDto
import io.aimc.reportingservice.entity.Portion
import org.springframework.stereotype.Component

@Component
class PortionMapper {
    fun fromDto(dto: PortionDto) = Portion(
        dto.id,
        dto.letterIds,
        dto.localDateTime
    )
}
