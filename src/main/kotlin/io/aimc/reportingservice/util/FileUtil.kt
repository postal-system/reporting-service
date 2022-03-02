package io.aimc.reportingservice.util

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import java.io.File
import java.time.LocalDate
import org.springframework.core.io.Resource

class FileUtil {
    // TODO: 02.03.2022 как внедрять зависимости в утилитный класс?
    companion object Factory {
        fun toXml(directory: String, filename: LocalDate, data: Any) {
            File(directory).mkdir()
            XmlMapper().writeValue(File("$directory/$filename.xml"), data)
        }
    }
}