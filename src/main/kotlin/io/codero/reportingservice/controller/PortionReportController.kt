package io.codero.reportingservice.controller

import io.codero.reportingservice.service.facade.PortionReportFacade
import java.time.LocalDate
import org.springframework.core.io.InputStreamResource
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
@RequestMapping("/api/reports/portions")
class PortionReportController(
    private val facade: PortionReportFacade
) {
    @GetMapping("/date")
    fun getReportByDate(
        @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate,
        @RequestParam("type") type: String
    ): ResponseEntity<Resource> {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_OCTET_STREAM;
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=$date.$type")
        return ResponseEntity.ok()
            .headers(headers)
            .body(InputStreamResource(facade.getReportByDateFile(date, type)))
    }

    @GetMapping("/week")
    fun getReportByWeek(
        @RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") fromDate: LocalDate,
        @RequestParam("type") type: String
    ): ResponseEntity<Resource> {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_OCTET_STREAM;
        headers.set(
            HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=" + fromDate.minusWeeks(1) + "_" + fromDate + "." + type
        )
        return ResponseEntity.ok()
            .headers(headers)
            .body(InputStreamResource(facade.getReportByWeekFile(fromDate, type)))
    }
}