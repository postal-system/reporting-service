package io.aimc.reportingservice.facade

import io.aimc.reportingservice.model.Report
import io.aimc.reportingservice.service.ReportService
import io.aimc.reportingservice.service.impl.ConvertServiceImpl
import io.aimc.reportingservice.service.impl.PortionServiceImpl
import java.io.InputStream
import java.time.LocalDate
import org.springframework.stereotype.Component

@Component
class ReportFacade(
    private val reportService: ReportService,
    // TODO: 09.03.2022 определиться с сущностью reporting или portion
    private val portionService: PortionServiceImpl,
    private val convertServiceImpl: ConvertServiceImpl,
) {

    fun getReportByDate(date: LocalDate, type: String): InputStream {
        val report = reportService.getReportByDate(date)

        return reportToFile(report, type)
    }

    fun getReportByWeek(fromDate: LocalDate, type: String): InputStream {
        val report = reportService.getReportByWeek(fromDate)

        return reportToFile(report, type)
    }

    fun reportToFile(report: Report, type: String): InputStream {
        return when (type) {
            "xml" -> convertServiceImpl.toXml(report)
            "csv" -> convertServiceImpl.toCsv(report)
            "xlsx" -> convertServiceImpl.toXlsx(report)
            else -> throw Exception("Was choose wrong type")
        }
    }

    //--------------------------------------------------------------
//    fun getReportUniqueSenderByDate(fromDate: LocalDate, type: String): InputStream {
//        //получить лист писем
//        val letterIds: List<UUID> = portionService.getLetterIdsByTimes(fromDate).flatMap { it.letterIds }
//
//        //отправить запрос по списку писем
//
//        //конвертировать результат в файл
//
////        val report = letterClient
//            return uniqueToFile(report, type)
//    }

    fun uniqueToFile(report: Report, type: String): InputStream {
        return when (type) {
            "xml" -> convertServiceImpl.toXml(report)
            "csv" -> convertServiceImpl.toCsv(report)
            "xlsx" -> convertServiceImpl.toXlsx(report)
            else -> throw Exception("Was choose wrong type")
        }
    }

}
