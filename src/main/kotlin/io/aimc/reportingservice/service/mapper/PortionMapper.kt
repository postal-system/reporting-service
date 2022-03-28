package io.aimc.reportingservice.service.mapper

import io.aimc.reportingservice.controller.dto.PortionDto
import io.aimc.reportingservice.domain.model.Portion
import org.springframework.stereotype.Component

@Component
class PortionMapper {
    // TODO: 28.03.2022 разобраться с маппиногом попробовать прикурутить мапстракт
    fun fromDto(dto: PortionDto) = Portion(
        dto.id,
        dto.letterIds,
        dto.localDateTime
    )
}
