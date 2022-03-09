package io.aimc.reportingservice.controller

import io.aimc.reportingservice.facade.ReportFacade
import java.time.LocalDate
import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.Resource
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpHeaders
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
    fun getReportByDate(
        @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate,
        @RequestParam("type") type: String
    ): ResponseEntity<Resource> {
        val header = HttpHeaders()
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + date + "." + type)
        return ResponseEntity.ok()
            .headers(header)
            .body(InputStreamResource(reportFacade.getReportByDate(date, type)))
    }

    @GetMapping("/week")
    fun getReportByWeek(
        @RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") fromDate: LocalDate,
        @RequestParam("type") type: String
    ): ResponseEntity<Resource> {
        val header = HttpHeaders()
        header.set(
            HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=" + fromDate.minusWeeks(1) + "_" + fromDate + "." + type
        )
        return ResponseEntity.ok()
            .headers(header)
            .body(InputStreamResource(reportFacade.getReportByWeek(fromDate, type)))
    }
//
//    @GetMapping("/unique-sender-count")
//    fun getSenderByTime(
//        @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate,
//        @RequestParam("type") type: String
//    ): ResponseEntity<Resource> {
//        val header = HttpHeaders()
//        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + date + "." + type)
//        return ResponseEntity.ok()
//            .headers(header)
//            .body(InputStreamResource(reportFacade.getReportUniqueSenderByDate(date, type)))
//    }
}