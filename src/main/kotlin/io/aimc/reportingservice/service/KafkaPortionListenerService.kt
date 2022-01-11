package io.aimc.reportingservice.service

import io.aimc.reportingservice.dto.PortionDto
import io.aimc.reportingservice.facade.PortionFacade
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaPortionListenerService(private val portionFacade: PortionFacade) {
    @KafkaListener(topics = ["\${spring.kafka.portion-topic}"])
    fun getPortion(portionDto: PortionDto) {
        portionFacade.addPortion(portionDto)
    }
}
