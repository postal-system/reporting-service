package io.codero.reportingservice.service.facade

import io.codero.reportingservice.domain.generator.PortionReportGenerator
import io.codero.reportingservice.domain.model.PortionReport
import io.codero.reportingservice.service.PortionReportServiceImpl
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
