package io.aimc.reportingservice.listener

import io.aimc.reportingservice.dto.PortionDto
import io.aimc.reportingservice.facade.PortionFacade
import io.aimc.reportingservice.util.LoggerDelegate
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
