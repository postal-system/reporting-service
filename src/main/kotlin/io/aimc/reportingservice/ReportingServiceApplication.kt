package io.aimc.reportingservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class ReportingServiceApplication

fun main(args: Array<String>) {
    runApplication<ReportingServiceApplication>(*args)
}
