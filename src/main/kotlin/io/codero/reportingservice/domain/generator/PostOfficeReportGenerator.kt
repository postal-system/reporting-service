package io.codero.reportingservice.domain.generator

import io.codero.reportingservice.domain.model.PostOfficeReport
import java.io.InputStream

interface PostOfficeReportGenerator {
    fun getType(): String
    fun generate(report: List<PostOfficeReport>): InputStream
}
