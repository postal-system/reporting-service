package io.aimc.reportingservice.domain.model

import java.sql.Date
import java.time.LocalDate

interface IReportPortion {
    fun getPortionAmount(): Long
    fun getLetterAmount(): Long
    fun getSendingDate(): Date
}