package io.aimc.reportingservice.service.portiongenerator

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import io.aimc.reportingservice.domain.generator.PortionReportGenerator
import io.aimc.reportingservice.domain.model.PortionReport
import java.io.InputStream
import org.springframework.stereotype.Component

@Component
class PortionXmlGenerator : PortionReportGenerator {

    override fun getType() = "xml"

    override fun generate(report: List<PortionReport>): InputStream {
        val xml: String = XmlMapper().writeValueAsString(report)
        return xml.byteInputStream()
    }
}