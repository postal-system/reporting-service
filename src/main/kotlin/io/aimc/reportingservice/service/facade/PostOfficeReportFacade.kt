package io.aimc.reportingservice.service.facade

import io.aimc.reportingservice.client.LetterClient
import io.aimc.reportingservice.controller.dto.LetterDto
import io.aimc.reportingservice.domain.PortionService
import io.aimc.reportingservice.domain.generator.PostOfficeReportGenerator
import io.aimc.reportingservice.domain.model.PostOfficeReport
import java.io.InputStream
import java.time.LocalDate
import java.util.UUID
import org.springframework.stereotype.Component

@Component
class PostOfficeReportFacade(
    private val letterClient: LetterClient,
    private val portionService: PortionService,
    private val generators: List<PostOfficeReportGenerator>
) {
    private val generatorsByType = generators.associateBy { it.getType() }

    private fun convert(report: List<PostOfficeReport>, type: String): InputStream {
        val generator = generatorsByType[type]
        if (generator != null) {
            return generator.generate(report)
        }
        throw IllegalArgumentException("Cannot convert to $type type")
    }

    fun getReportByDate(fromDate: LocalDate): List<PostOfficeReport> {
        val letterIds: List<UUID> = portionService.getPortionsByDate(fromDate).flatMap { it.letterIds }
        val letters: List<LetterDto> = letterClient.getByListId(letterIds)
        return letters.groupBy { it.postOfficeId }.map { PostOfficeReport(it.key.toString(), it.value.size) }
    }

    fun getReportByWeek(fromDate: LocalDate): List<PostOfficeReport> {
        val letterIds: List<UUID> = portionService.getPortionsByLastWeek(fromDate).flatMap { it.letterIds }
        val letters: List<LetterDto> = letterClient.getByListId(letterIds)
        return letters.groupBy { it.postOfficeId }.map { PostOfficeReport(it.key.toString(), it.value.size) }
    }

    fun getReportByDateFile(date: LocalDate, type: String): InputStream {
        val senderReports = getReportByDate(date)
        return convert(senderReports, type)
    }

    fun getReportByWeekFile(date: LocalDate, type: String): InputStream {
        val senderReports = getReportByWeek(date)
        return convert(senderReports, type)
    }
}
