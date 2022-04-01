package io.aimc.reportingservice.service.sendergenerator

import io.aimc.reportingservice.domain.generator.PostOfficeReportGenerator
import io.aimc.reportingservice.domain.model.PostOfficeReport
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.PrintWriter
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import org.springframework.stereotype.Component

@Component
class PostOfficeCsvGenerator : PostOfficeReportGenerator {

    override fun getType() = "csv"

    override fun generate(report: List<PostOfficeReport>): InputStream {
        val csvFormat = CSVFormat.DEFAULT.withHeader("sender", "letterAmount")
        val out = ByteArrayOutputStream()
        out.use {
            val printer = CSVPrinter(PrintWriter(out), csvFormat)

            report.forEach {
                printer.printRecord(it.postOffice , it.letterAmount)
            }

            printer.flush()
            return out.toByteArray().inputStream()
        }
    }
}