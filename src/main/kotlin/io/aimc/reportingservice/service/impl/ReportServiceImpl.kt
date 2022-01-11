package io.aimc.reportingservice.service.impl

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import io.aimc.reportingservice.entity.Portion
import io.aimc.reportingservice.model.Report
import io.aimc.reportingservice.repository.PortionRepository
import io.aimc.reportingservice.service.ReportService
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.io.File
import java.time.LocalDate

@Service
class ReportServiceImpl(
    private val portionRepository: PortionRepository,
    private val xmlMapper: XmlMapper = XmlMapper(),
    @Value("\${reports.directory}")
    private val directory: String
) : ReportService {

    override fun getReportByDate(date: LocalDate): Report {
        val portionAmount: Int = portionRepository.count(dateEqual(date)).toInt()
        val shipmentAmount: Int = portionRepository.countShipmentByDate(date)
        val report = Report(portionAmount, shipmentAmount)
        File(directory).mkdir()
        xmlMapper.writeValue(File("$directory/$date.xml"), report)
        return report
    }

    override fun getReportByWeek(fromDate: LocalDate): Report {
        val portionAmount: Int = portionRepository.countPortionByWeek(fromDate)
        val shipmentAmount: Int = portionRepository.countShipmentByWeek(fromDate)
        val report = Report(portionAmount, shipmentAmount)
        File(directory).mkdir()
        xmlMapper.writeValue(File("$directory/$fromDate-${fromDate.plusWeeks(1)}.xml"), report)
        return report
    }

    private fun dateEqual(date: LocalDate): Specification<Portion> {
        return Specification<Portion> { root, query, criteriaBuilder ->
            criteriaBuilder.equal(root.get<LocalDate>("date"), date)
        }
    }

}