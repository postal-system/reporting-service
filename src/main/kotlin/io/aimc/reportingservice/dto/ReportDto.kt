package io.aimc.reportingservice.dto

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement
data class ReportDto(
    val portionAmount: Int,
    val shipmentAmount: Int
)
