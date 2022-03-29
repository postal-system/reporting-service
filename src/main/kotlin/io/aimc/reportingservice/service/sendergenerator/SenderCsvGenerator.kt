package io.aimc.reportingservice.service.sendergenerator

import io.aimc.reportingservice.domain.generator.SenderReportGenerator
import io.aimc.reportingservice.domain.model.SenderReport
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.PrintWriter
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import org.springframework.stereotype.Component

@Component
class SenderCsvGenerator : SenderReportGenerator {

    override fun getType() = "csv"

    override fun generate(report: List<SenderReport>): InputStream {
        val csvFormat = CSVFormat.DEFAULT.withHeader("Sender", "Amount of sendings")
        val out = ByteArrayOutputStream()
        out.use {
            val printer = CSVPrinter(PrintWriter(out), csvFormat)

            report.forEach {
                printer.printRecord(it.sender , it.letterAmount)
            }

            printer.flush()
            return out.toByteArray().inputStream()
        }
    }
}