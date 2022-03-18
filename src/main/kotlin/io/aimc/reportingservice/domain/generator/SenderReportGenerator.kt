package io.aimc.reportingservice.domain.generator

import io.aimc.reportingservice.domain.model.SenderReport
import java.io.InputStream

interface SenderReportGenerator {
    fun getType(): String
    fun generate(report: List<SenderReport>): InputStream
}
