package io.aimc.reportingservice.facade

import io.aimc.reportingservice.dto.PortionDto
import io.aimc.reportingservice.dto.ReportDto
import io.aimc.reportingservice.mapper.PortionMapper
import io.aimc.reportingservice.mapper.ReportMapper
import io.aimc.reportingservice.service.PortionService
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class PortionFacade(
    private val portionService: PortionService,
    private val portionMapper: PortionMapper,
    private val reportMapper: ReportMapper
) {
    fun addPortion(portionDto: PortionDto) {
        portionService.addPortion(portionMapper.fromDto(portionDto))
    }

    fun getReportByDate(date: LocalDate): ReportDto {
        return reportMapper.toDto(portionService.getReportByDate(date))
    }

//    fun getReportByWeek(date: Instant): ReportDto {
//        return reportMapper.toDto(portionService.getReportByWeek(date))
//    }
}
