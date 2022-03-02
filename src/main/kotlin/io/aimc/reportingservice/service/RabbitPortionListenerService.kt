package io.aimc.reportingservice.service

import io.aimc.reportingservice.dto.PortionDto
import io.aimc.reportingservice.facade.PortionFacade
import org.springframework.stereotype.Service

@Service
class RabbitPortionListenerService(private val portionFacade: PortionFacade) {
//    @RabbitListener(queues = ["\${spring.rabbitmq.portion-queue}"])
    fun getPortion(portionDto: PortionDto) {
        portionFacade.addPortion(portionDto)
        println(portionDto)
    }
}
