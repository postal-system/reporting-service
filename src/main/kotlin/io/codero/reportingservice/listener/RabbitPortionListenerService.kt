package io.codero.reportingservice.listener

import io.codero.reportingservice.controller.dto.PortionDto
import io.codero.reportingservice.service.facade.PortionFacade
import org.springframework.stereotype.Service

@Service
class RabbitPortionListenerService(private val portionFacade: PortionFacade) {
//    @RabbitListener(queues = ["\${spring.rabbitmq.portion-queue}"])
    fun getPortion(portionDto: PortionDto) {
        portionFacade.addPortion(portionDto)
        println(portionDto)
    }
}
