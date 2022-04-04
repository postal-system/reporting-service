package io.codero.reportingservice.domain

import io.codero.reportingservice.domain.model.Portion
import java.time.LocalDate

interface PortionService {
    fun addRawPortion(portion: Portion)
    fun getPortionsByDate(fromDate: LocalDate): List<Portion>
    fun getPortionsByLastWeek(fromDate: LocalDate): List<Portion>
}
