package io.codero.reportingservice.domain.generator

import io.codero.reportingservice.domain.model.PortionReport
import java.io.InputStream

interface PortionReportGenerator {
    fun getType(): String
    fun generate(report: List<PortionReport>): InputStream
}
