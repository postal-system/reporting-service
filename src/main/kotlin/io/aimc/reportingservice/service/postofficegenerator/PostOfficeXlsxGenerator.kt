package io.aimc.reportingservice.service.sendergenerator

import io.aimc.reportingservice.domain.generator.PostOfficeReportGenerator
import io.aimc.reportingservice.domain.model.PostOfficeReport
import java.io.ByteArrayOutputStream
import java.io.InputStream
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Component

@Component
class PostOfficeXlsxGenerator : PostOfficeReportGenerator {

    override fun getType() = "xlsx"

    override fun generate(report: List<PostOfficeReport>): InputStream {
        val workbook: Workbook = XSSFWorkbook()
        val out = ByteArrayOutputStream()
        out.use {
            workbook.use {
                val sheet: Sheet = workbook.createSheet("Report")

                val header: Row = sheet.createRow(0)

                var headerCell: Cell = header.createCell(0)
                headerCell.setCellValue("Post office")

                headerCell = header.createCell(1)
                headerCell.setCellValue("amount of letter")

                var i: Int = 1

                report.forEach {
                    val row = sheet.createRow(i)

                    var cell = row.createCell(0)
                    cell.setCellValue(it.postOffice)

                    cell = row.createCell(1)
                    cell.setCellValue(it.letterAmount.toString())
                    i++
                }

                workbook.write(out)
            }
            return out.toByteArray().inputStream()
        }
    }
}