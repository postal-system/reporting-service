package io.aimc.reportingservice.service.impl

import io.aimc.reportingservice.model.Report
import io.aimc.reportingservice.model.custom.ICountPortion
import io.aimc.reportingservice.repository.PortionRepository
import io.aimc.reportingservice.service.ReportService
import java.time.LocalDate
import org.springframework.stereotype.Service

@Service
class ReportServiceImpl(
    private val portionRepository: PortionRepository,
) : ReportService {

    override fun getReportByDate(date: LocalDate): Report {
        val obj: ICountPortion = portionRepository.countDate(date).get(0)

        return Report(obj.getPortionAmount(), obj.getLetterAmount())
    }

    override fun getReportByWeek(fromDate: LocalDate): Report {
//        return portionRepository.countDate(fromDate).stream().findFirst().get()
        return Report(1, 1)
    }

//    fun getLetterIdsByTimes(fromDate: LocalDate): List<Portion> {
//        return portionRepository.getLetterIdsByTimes(fromDate)
//    }
}