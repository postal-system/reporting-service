package io.aimc.reportingservice.service

import io.aimc.reportingservice.model.Report
import java.io.InputStream

interface ConvertService {
    fun toXml(report: Report): InputStream
    fun toCsv(report: Report): InputStream
    fun toXlsx(report: Report): InputStream
}