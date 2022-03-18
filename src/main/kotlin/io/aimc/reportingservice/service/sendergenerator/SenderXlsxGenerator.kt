package io.aimc.reportingservice.service.sendergenerator

import io.aimc.reportingservice.domain.generator.SenderReportGenerator
import io.aimc.reportingservice.domain.model.SenderReport
import java.io.ByteArrayOutputStream
import java.io.InputStream
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Component

@Component
class SenderXlsxGenerator : SenderReportGenerator {

    override fun getType() = "xlsx"

    override fun generate(report: List<SenderReport>): InputStream {
        val workbook: Workbook = XSSFWorkbook()
        val out = ByteArrayOutputStream()
        out.use {
            workbook.use {
                // создать страницу
                val sheet: Sheet = workbook.createSheet("Report")

                // создать заголовки
                val header: Row = sheet.createRow(0)

                var headerCell: Cell = header.createCell(0)
                headerCell.setCellValue("sender")

                headerCell = header.createCell(1)
                headerCell.setCellValue("letterAmount")

                var i: Int = 1
//                 заполнить данными
                report.forEach {
                    val row = sheet.createRow(i)

                    var cell = row.createCell(0)
                    cell.setCellValue(it.sender)

                    cell = row.createCell(1)
                    cell.setCellValue(it.letterAmount.toString())
                    i++
                }

                // записать в результат
                workbook.write(out)
            }
            return out.toByteArray().inputStream()
        }
    }
}