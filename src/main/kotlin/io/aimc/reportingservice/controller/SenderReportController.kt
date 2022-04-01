package io.aimc.reportingservice.controller

import io.aimc.reportingservice.service.facade.SenderReportFacade
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
@RequestMapping("/api/reports/senders")
class SenderReportController(
    private val facade: SenderReportFacade
) {
    @GetMapping("/date")
    fun getSenderByDate(
        @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate,
        @RequestParam("type") type: String
    ): ResponseEntity<Resource> {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_OCTET_STREAM;
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=date:$date.$type")
        return ResponseEntity.ok()
            .headers(headers)
            .body(InputStreamResource(facade.getReportDateFile(date, type)))
    }

    @GetMapping("/week")
    fun getSenderByWeek(
        @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate,
        @RequestParam("type") type: String
    ): ResponseEntity<Resource> {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_OCTET_STREAM;
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=week: $date.$type")
        return ResponseEntity.ok()
            .headers(headers)
            .body(InputStreamResource(facade.getReportWeekFile(date, type)))
    }
}