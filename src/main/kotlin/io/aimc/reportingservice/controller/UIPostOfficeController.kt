package io.aimc.reportingservice.controller

import io.aimc.reportingservice.service.facade.PostOfficeReportFacade
import java.time.LocalDate
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/post-office/ui/table")
class UIPostOfficeController(
    private val facade: PostOfficeReportFacade
) {
    @GetMapping("/date")
    fun getTableDate(
        @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate,
        model: Model
    ): String {
        val list = facade.getReportByDate(date)
            .map {
                listOf(
                    it.postOffice,
                    it.letterAmount
                )
            }

        model.addAttribute("chartData", list)

        return "office"
    }


    @GetMapping("/week")
    fun getTableWeek(
        @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate,
        model: Model
    ): String {
        val list = facade.getReportByWeek(date)
            .map {
                listOf(
                    it.postOffice,
                    it.letterAmount
                )
            }
        model.addAttribute("chartData", list)

        return "office"
    }
}
