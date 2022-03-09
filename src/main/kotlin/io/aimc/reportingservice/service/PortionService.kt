package io.aimc.reportingservice.service

import io.aimc.reportingservice.entity.Portion
import java.time.LocalDate

interface PortionService {
    fun addRawPortion(portion: Portion)
    fun getLetterIdsByTimes(fromDate: LocalDate): List<Portion>
}
