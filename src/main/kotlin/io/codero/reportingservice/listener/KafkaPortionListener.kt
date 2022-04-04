package io.codero.reportingservice.listener

import io.codero.reportingservice.controller.dto.PortionDto
import io.codero.reportingservice.service.facade.PortionFacade
import io.codero.reportingservice.util.LoggerDelegate
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaPortionListener(private val portionFacade: PortionFacade) {
    private val logger by LoggerDelegate()

    @KafkaListener(topics = ["\${spring.kafka.portion-topic}"])
    fun getPortion(dto: PortionDto) {
        logger.info("#### -> $dto")
        portionFacade.addPortion(dto)
    }
}
