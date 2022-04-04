package io.codero.reportingservice.conf

import io.codero.reportingservice.controller.dto.PortionDto
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer

@EnableKafka
@Configuration
class KafkaConsumerConfig {
    @Value("\${spring.kafka.bootstrap-servers}")
    private val bootstrapServers: String? = null

    @Value("\${spring.kafka.consumer.groupId}")
    private val groupId: String? = null

    @Bean
    fun consumerFactory(): ConsumerFactory<String, PortionDto> {
        val props: MutableMap<String, Any?> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        props[ConsumerConfig.GROUP_ID_CONFIG] = groupId
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java
        return DefaultKafkaConsumerFactory(
            props,
            StringDeserializer(),
            JsonDeserializer(PortionDto::class.java)
        )
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, PortionDto> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, PortionDto>()
        factory.consumerFactory = consumerFactory()
        return factory
    }
}
