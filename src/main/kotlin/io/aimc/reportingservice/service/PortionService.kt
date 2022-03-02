package io.aimc.reportingservice.service

import io.aimc.reportingservice.entity.Portion

interface PortionService {
    fun addRawPortion(portion: Portion)
}
