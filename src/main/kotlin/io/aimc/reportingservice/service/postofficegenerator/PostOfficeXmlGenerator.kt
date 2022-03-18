package io.aimc.reportingservice.service.sendergenerator

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator
import io.aimc.reportingservice.domain.generator.PostOfficeReportGenerator
import io.aimc.reportingservice.domain.model.PostOfficeReport
import java.io.InputStream
import org.springframework.stereotype.Component

@Component
class PostOfficeXmlGenerator : PostOfficeReportGenerator {

    override fun getType() = "xml"

    override fun generate(report: List<PostOfficeReport>): InputStream {
        val xmlMapper = XmlMapper()
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
        val xml: String = xmlMapper.writeValueAsString(report)
        return xml.byteInputStream()
    }
}