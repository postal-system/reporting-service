package io.aimc.reportingservice.service.facade

import io.aimc.reportingservice.client.LetterClient
import io.aimc.reportingservice.controller.dto.LetterDto
import io.aimc.reportingservice.domain.PortionService
import io.aimc.reportingservice.domain.generator.SenderReportGenerator
import io.aimc.reportingservice.domain.model.SenderReport
import java.io.InputStream
import java.time.LocalDate
import java.util.UUID
import org.springframework.stereotype.Component

@Component
class SenderReportFacade(
    private val letterClient: LetterClient,
    private val portionService: PortionService,
    private val generators: List<SenderReportGenerator>
) {
    private val generatorsByType = generators.associateBy { it.getType() }

    fun getReportDateFile(date: LocalDate, type: String): InputStream {
        val reports: List<SenderReport> = getReportDate(date)
        return convert(reports, type)
    }

    fun getReportWeekFile(date: LocalDate, type: String): InputStream {
        val reports: List<SenderReport> = getReportWeek(date)
        return convert(reports, type)
    }

    private fun convert(reports: List<SenderReport>, type: String): InputStream {
        val generator = generatorsByType[type]
        if (generator != null) {
            return generator.generate(reports)
        }
        throw IllegalArgumentException("Cannot convert to $type type")
    }

    // TODO: 28.03.2022 Избавиться от дублирования в фасаде перенести сюда работу с датами
    fun getReportDate(fromDate: LocalDate): List<SenderReport> {
        val letterIds: List<UUID> = portionService.getPortionsByDate(fromDate).flatMap { it.letterIds }
        val letters: List<LetterDto> = letterClient.getByListId(letterIds)
        return letters.groupBy { it.sender }.map { SenderReport(it.key, it.value.size) }.toList()
    }

    fun getReportWeek(fromDate: LocalDate): List<SenderReport> {
        val letterIds: List<UUID> = portionService.getPortionsByLastWeek(fromDate).flatMap { it.letterIds }
        val letters: List<LetterDto> = letterClient.getByListId(letterIds)
        return letters.groupBy { it.sender }.map { SenderReport(it.key, it.value.size) }.toList()
    }
}
