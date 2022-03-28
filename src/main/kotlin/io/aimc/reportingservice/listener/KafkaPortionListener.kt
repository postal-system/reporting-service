package io.aimc.reportingservice.listener

import io.aimc.reportingservice.controller.dto.PortionDto
import io.aimc.reportingservice.service.facade.PortionFacade
import io.aimc.reportingservice.util.LoggerDelegate
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaPortionListener(private val portionFacade: PortionFacade) {
    private val logger by LoggerDelegate()

    @Value("\${spring.kafka.consumer.topic}")
    private val topic: String? = null

    @KafkaListener(topics = ["\${spring.kafka.portion-topic}"])
    fun getPortion(dto: PortionDto) {
        logger.info("$topic: #### -> $dto")
        portionFacade.addPortion(dto)
    }
}
