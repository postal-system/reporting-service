package io.aimc.reportingservice.controller

import io.aimc.reportingservice.dto.ReportDto
import io.aimc.reportingservice.facade.PortionFacade
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/api/portions")
class PortionController(private val portionFacade: PortionFacade) {

    @GetMapping(produces = [MediaType.APPLICATION_XML_VALUE])
    fun getReportByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate): ReportDto {
        return portionFacade.getReportByDate(date)
    }

//    @GetMapping(produces = [MediaType.APPLICATION_XML_VALUE])
//    fun getReportByWeek(@RequestParam("date") @DateTimeFormat(pattern = "dd.MM.yyyy") date: Instant): ReportDto {
//        return portionFacade.getReportByWeek(date)
//    }
}