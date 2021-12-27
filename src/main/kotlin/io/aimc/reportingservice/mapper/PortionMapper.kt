package io.aimc.reportingservice.mapper

import io.aimc.reportingservice.dto.PortionDto
import io.aimc.reportingservice.entity.Portion
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.springframework.stereotype.Component

@Mapper(componentModel = "spring")
@Component
interface PortionMapper {
    fun toDto(portion: Portion):PortionDto
    @Mapping(target = "timestamp", expression = "java(Instant.now())")
    fun fromDto(portionDto: PortionDto):Portion
}
