package io.aimc.reportingservice.mapper

import io.aimc.reportingservice.dto.ReportDto
import io.aimc.reportingservice.model.Report
import org.mapstruct.Mapper
import org.springframework.stereotype.Component

@Mapper(componentModel = "spring")
@Component
interface ReportMapper {
    fun toDto(report: Report): ReportDto
    fun fromDto(reportDto: ReportDto): Report
}