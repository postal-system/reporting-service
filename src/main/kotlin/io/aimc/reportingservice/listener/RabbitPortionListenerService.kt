package io.aimc.reportingservice.listener

import io.aimc.reportingservice.controller.dto.PortionDto
import io.aimc.reportingservice.service.facade.PortionFacade
import org.springframework.stereotype.Service

@Service
class RabbitPortionListenerService(private val portionFacade: PortionFacade) {
//    @RabbitListener(queues = ["\${spring.rabbitmq.portion-queue}"])
    fun getPortion(portionDto: PortionDto) {
        portionFacade.addPortion(portionDto)
        println(portionDto)
    }
}
