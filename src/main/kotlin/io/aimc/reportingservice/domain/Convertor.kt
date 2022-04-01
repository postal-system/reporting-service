package io.aimc.reportingservice.domain

import java.io.InputStream

interface Convertor {
    fun toXml(): InputStream
    fun toCsv(): InputStream
    fun toXlsx(): InputStream
}
