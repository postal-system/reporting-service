package io.aimc.reportingservice.controller

import io.aimc.reportingservice.service.facade.PortionReportFacade
import java.time.LocalDate
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/portion/ui/table")
class UIPortionController(
    private val facade: PortionReportFacade
) {
    @GetMapping("/date")
    fun getTableDate(
        @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate,
        model: Model
    ): String {
        val list = facade.getReportDate(date)
            .map {
                listOf(
                    it.portionAmount.toString(),
                    it.letterAmount.toString(),
                    it.sendingDate
                )
            }

        model.addAttribute("chartData", list)

        return "portion"
    }

    @GetMapping("/week")
    fun getTableWeek(
        @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate,
        model: Model
    ): String {
        val list = facade.getReportWeek(date)
            .map {
                listOf(
                    it.portionAmount.toString(),
                    it.letterAmount.toString(),
                    it.sendingDate.toString()
                )
            }

        model.addAttribute("chartData", list)

        return "portion"
    }
}