package io.codero.reportingservice.domain.generator

import io.codero.reportingservice.domain.model.SenderReport
import java.io.InputStream

interface SenderReportGenerator {
    fun getType(): String
    fun generate(report: List<SenderReport>): InputStream
}
