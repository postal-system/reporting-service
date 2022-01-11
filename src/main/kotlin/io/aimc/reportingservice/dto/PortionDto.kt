package io.aimc.reportingservice.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class PortionDto(
    @JsonProperty("id")
    val id: UUID,
    @JsonProperty("shipmentIds")
    val shipmentIds: MutableList<UUID>
)
