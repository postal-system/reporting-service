package io.aimc.reportingservice.service

import io.aimc.reportingservice.dto.PortionDto
import io.aimc.reportingservice.facade.PortionFacade
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class PortionListenerService(private val portionFacade: PortionFacade) {
    @KafkaListener(topics = ["\${spring.kafka.portion-topic}"])//todo добавить отдельные классы для кафки и рэббит
    @RabbitListener(queues = ["\${spring.rabbitmq.portion-queue}"])
    fun getPortion(portionDto: PortionDto) {
        portionFacade.addPortion(portionDto)
    }
}
