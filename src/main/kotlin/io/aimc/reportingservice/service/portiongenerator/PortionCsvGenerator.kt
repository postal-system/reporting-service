package io.aimc.reportingservice.service.portiongenerator

import io.aimc.reportingservice.domain.generator.PortionReportGenerator
import io.aimc.reportingservice.domain.model.PortionReport
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.PrintWriter
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import org.springframework.stereotype.Component

@Component
class PortionCsvGenerator : PortionReportGenerator {

    override fun getType() = "csv"

    override fun generate(report: List<PortionReport>): InputStream {
        val csvFormat = CSVFormat.DEFAULT.withHeader("portionAmount", "letterAmount", "Date")
        val out = ByteArrayOutputStream()
        out.use {
            val printer = CSVPrinter(PrintWriter(out), csvFormat)
            report.forEach { printer.printRecord(it.portionAmount, it.letterAmount, it.sendingDate) }
            printer.flush()
            return out.toByteArray().inputStream()
        }
    }
}