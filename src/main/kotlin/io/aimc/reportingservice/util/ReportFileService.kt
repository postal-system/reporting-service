package io.aimc.reportingservice.util

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import io.aimc.reportingservice.model.Report
import java.io.ByteArrayOutputStream
import java.io.PrintWriter
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service

@Service
class ReportFileService {
    fun toXml(report: Report): Resource {
        val xml = XmlMapper().writeValueAsString(report)
        return InputStreamResource(xml.byteInputStream())
    }

    fun toCsv(report: Report): Resource {
        val csvFormat = CSVFormat.DEFAULT.withHeader("portionAmount", "letterAmount")
        val out = ByteArrayOutputStream()
        out.use {
            val printer = CSVPrinter(PrintWriter(out), csvFormat)
            printer.printRecord(report.portionAmount, report.letterAmount)
            printer.flush()
            return InputStreamResource(out.toByteArray().inputStream())
        }
    }

    fun toXlsx(report: Report): Resource {
        val workbook: Workbook = XSSFWorkbook()
        val out = ByteArrayOutputStream()
        out.use {
            workbook.use {
                // создать страницу
                val sheet: Sheet = workbook.createSheet("Report")
                // создать заголовки
                val header: Row = sheet.createRow(0)

                var headerCell: Cell = header.createCell(0)
                headerCell.setCellValue("portion")

                headerCell = header.createCell(1)
                headerCell.setCellValue("letter")
                // заполнить данными
                val row = sheet.createRow(1)

                var cell = row.createCell(0)
                cell.setCellValue(report.portionAmount.toString())

                cell = row.createCell(1)
                cell.setCellValue(report.letterAmount.toString())
                // записать в файл
                workbook.write(out)
            }
            return InputStreamResource(out.toByteArray().inputStream())
        }
    }
}
