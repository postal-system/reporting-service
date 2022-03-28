package io.aimc.reportingservice.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import java.util.UUID

data class PortionDto(
    @JsonProperty("id")
    val id: UUID,
    @JsonProperty("letterIds")
    val letterIds: List<UUID>,
    // TODO: 28.03.2022 Привести к единому типу во всех сервисах или определиться с названием переменной
    @JsonProperty("localDateTime")
    val localDateTime: LocalDateTime
)
