package io.aimc.reportingservice.dto

import java.util.*

data class PortionDto(
    val id: UUID? = null,
    val shipmentIds: MutableList<UUID>? = null
)
