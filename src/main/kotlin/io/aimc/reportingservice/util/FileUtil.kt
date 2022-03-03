package io.aimc.reportingservice.util

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.Resource

class FileUtil {
    companion object Factory {
        fun toXml(data: Any): Resource {
            val xml = XmlMapper().writeValueAsString(data)
            return InputStreamResource(xml.byteInputStream())
        }
    }
}
