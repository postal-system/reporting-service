package io.aimc.reportingservice.controller

import io.aimc.reportingservice.service.facade.SenderReportFacade
import java.time.LocalDate
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/sender/ui/table")
class UISenderController(
    private val facade: SenderReportFacade
) {
    @GetMapping("/date")
    fun getTableDate(
        @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate,
        model: Model
    ): String {
        val list = facade.getReportDate(date)
            .map {
                listOf(
                    it.sender,
                    it.letterAmount
                )
            }

        model.addAttribute("chartData", list)
        return "sender"
    }

    @GetMapping("/week")
    fun getTableWeek(
        @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate,
        model: Model
    ): String {
        val list = facade.getReportWeek(date)
            .map {
                listOf(
                    it.sender,
                    it.letterAmount
                )
            }

        model.addAttribute("chartData", list)
        return "sender"
    }

}