package io.aimc.reportingservice.domain.generator

import io.aimc.reportingservice.domain.model.PortionReport
import java.io.InputStream

interface PortionReportGenerator {
    fun getType(): String
    fun generate(report: List<PortionReport>): InputStream
}
