package io.codero.reportingservice.controller.dto

import java.time.Instant
import java.util.UUID

data class LetterDto(
    val id: UUID,
    val sender: String,
    val content: String,
    val receiver: String,
    val timestamp: Instant,
    val postOfficeId: Int
//    val rawLetterDto: RawLetterDto
)
