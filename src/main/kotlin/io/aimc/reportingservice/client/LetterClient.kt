package io.aimc.reportingservice.client

import io.aimc.reportingservice.controller.dto.LetterDto
import java.util.UUID
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "letter-service", url = "http://localhost:8086")
interface LetterClient {

    @PostMapping(
        value = ["/api/letters/ids"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getByListId(@RequestBody letterIds: List<UUID>): List<LetterDto>
}