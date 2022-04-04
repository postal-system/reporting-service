package io.codero.reportingservice.domain.model

import java.sql.Date

interface IReportPortion {
    fun getPortionAmount(): Long
    fun getLetterAmount(): Long
    fun getSendingDate(): Date
}