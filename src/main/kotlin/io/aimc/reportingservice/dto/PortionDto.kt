package io.aimc.reportingservice.dto

import java.util.*

data class PortionDto(
    val id: UUID,
    val shipmentIds: MutableList<UUID>
)
