package io.aimc.reportingservice.service.sendergenerator

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator
import io.aimc.reportingservice.domain.generator.SenderReportGenerator
import io.aimc.reportingservice.domain.model.SenderReport
import java.io.InputStream
import org.springframework.stereotype.Component

@Component
class SenderXmlGenerator : SenderReportGenerator {
    // TODO: 28.03.2022 привести типы данных к enum

    override fun getType() = "xml"

    override fun generate(report: List<SenderReport>): InputStream {
        val xmlMapper = XmlMapper()
        xmlMapper.configure( ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true );
        val xml: String = xmlMapper.writeValueAsString(report)
        return xml.byteInputStream()
    }
}