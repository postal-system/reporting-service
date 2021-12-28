package io.aimc.reportingservice.dto.mapper

import io.aimc.reportingservice.dto.PortionDto
import io.aimc.reportingservice.entity.Portion
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class PortionMapper {
    fun fromDto(portionDto: PortionDto)=Portion(
        portionDto.id,
        portionDto.shipmentIds,
        LocalDate.now()
    )
}
