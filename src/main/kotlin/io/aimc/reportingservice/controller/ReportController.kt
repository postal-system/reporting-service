package io.aimc.reportingservice.controller

import io.aimc.reportingservice.facade.ReportFacade
import java.time.LocalDate
import org.springframework.core.io.Resource
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/reports")
class ReportController(
    private val reportFacade: ReportFacade
) {
    @GetMapping("/date")
    fun getReportByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate): ResponseEntity<Resource> {
        val header = HttpHeaders()
        header.setContentType(MediaType.APPLICATION_XML)
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + date)
        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_ATOM_XML)
            .headers(header)
            .body(reportFacade.getReportByDate(date))
    }

    @GetMapping("/week")
    fun getReportByWeek(@RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") fromDate: LocalDate): ResponseEntity<Resource> {
        val header = HttpHeaders()
        header.setContentType(MediaType.APPLICATION_XML)
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fromDate.minusWeeks(1) + "_" + fromDate)
        return ResponseEntity.ok()
            .headers(header)
            .body(reportFacade.getReportByWeek(fromDate))
    }

    @GetMapping("/date2")
    fun getReportByDate2(
        @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate,
        @RequestParam("type") type: String
    ): ResponseEntity<Resource> {
        val header = HttpHeaders()
//        header.setContentType(MediaType.APPLICATION_XML)
        header.setContentType(MediaType.APPLICATION_OCTET_STREAM)
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + date)
        return ResponseEntity.ok()
//            .contentType(MediaType.APPLICATION_ATOM_XML)
            .headers(header)
            .body(reportFacade.getReportByDate(date, type))
    }

}