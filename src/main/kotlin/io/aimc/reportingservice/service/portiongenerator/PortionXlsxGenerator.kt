package io.aimc.reportingservice.service.portiongenerator

import io.aimc.reportingservice.domain.generator.PortionReportGenerator
import io.aimc.reportingservice.domain.model.PortionReport
import java.io.ByteArrayOutputStream
import java.io.InputStream
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Component

@Component
class PortionXlsxGenerator : PortionReportGenerator {

    override fun getType() = "xlsx"

    override fun generate(report: List<PortionReport>): InputStream {
        val xssfWorkbook: Workbook = XSSFWorkbook()
        val out = ByteArrayOutputStream()
        out.use {
            xssfWorkbook.use {
                // создать страницу
                val sheet: Sheet = xssfWorkbook.createSheet("portionReport")

                // создать заголовки
                val header: Row = sheet.createRow(0)

                var headerCell: Cell = header.createCell(0)
                headerCell.setCellValue("portion")

                headerCell = header.createCell(1)
                headerCell.setCellValue("letter")

                headerCell = header.createCell(2)
                headerCell.setCellValue("date")

                var i = 1
                // заполнить данными
                report.forEach {
                    val row = sheet.createRow(i)
                    var cell = row.createCell(0)
                    cell.setCellValue(it.portionAmount.toString())

                    cell = row.createCell(1)
                    cell.setCellValue(it.letterAmount.toString())

                    cell = row.createCell(2)
                    cell.setCellValue(it.sendingDate.toString())

                    i++
                }

                // записать в результат
                xssfWorkbook.write(out)
                xssfWorkbook.close()
            }
        }
        return out.toByteArray().inputStream()
    }
}