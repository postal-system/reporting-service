package io.aimc.reportingservice.domain.generator

import io.aimc.reportingservice.domain.model.PostOfficeReport
import java.io.InputStream

interface PostOfficeReportGenerator {
    fun getType(): String
    fun generate(report: List<PostOfficeReport>): InputStream
}
