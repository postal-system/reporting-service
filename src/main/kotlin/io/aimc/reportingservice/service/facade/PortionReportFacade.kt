package io.aimc.reportingservice.service.facade

import io.aimc.reportingservice.domain.generator.PortionReportGenerator
import io.aimc.reportingservice.domain.model.PortionReport
import io.aimc.reportingservice.service.PortionReportServiceImpl
import java.io.InputStream
import java.time.LocalDate
import org.springframework.stereotype.Component

@Component
class PortionReportFacade(
    private val reportService: PortionReportServiceImpl,
    generators: List<PortionReportGenerator>
) {
    private val generatorsByType = generators.associateBy { it.getType() }

    fun convert(report: List<PortionReport>, type: String): InputStream {
        val generator = generatorsByType[type]
        if (generator != null) {
            return generator.generate(report)
        }
        // TODO: 11.03.2022 custom exception
        throw IllegalArgumentException("Cannot convert to $type type")
    }

    fun getReportDate(date: LocalDate): List<PortionReport> {
        return reportService.getByDate(date)
    }

    fun getReportWeek(fromDate: LocalDate): List<PortionReport> {
        return reportService.getByWeek(fromDate)
    }

    fun getReportByDateFile(date: LocalDate, type: String): InputStream {
        val report = getReportDate(date)
        return convert(report, type)
    }

    fun getReportByWeekFile(date: LocalDate, type: String): InputStream {
        val report = getReportWeek(date)
        return convert(report, type)
    }

}
