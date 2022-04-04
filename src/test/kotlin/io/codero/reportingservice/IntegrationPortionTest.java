package io.codero.reportingservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.codero.reportingservice.config.KafkaConsumerConfigTest;
import io.codero.reportingservice.config.KafkaProducerConfigTest;
import io.codero.reportingservice.controller.dto.PortionDto;
import io.codero.reportingservice.config.KafkaConsumerConfigTest;
import io.codero.reportingservice.config.KafkaProducerConfigTest;
import io.codero.reportingservice.controller.dto.PortionDto;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Import({KafkaProducerConfigTest.class, KafkaConsumerConfigTest.class})
public class IntegrationPortionTest extends AbstractControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Producer<String, PortionDto> producer;

    @Test
    public void shouldReturnPortionDtoFromRestIfWriteCreatePortionDtoToKafka() {
        PortionDto dto = getPortionDto();
        producer.send(new ProducerRecord<>("portions", dto));
    }

    private PortionDto getPortionDto() {
        UUID id = UUID.randomUUID();
        List<UUID> lettersIds = List.of(UUID.randomUUID(), UUID.randomUUID());
        LocalDateTime timeStamp = LocalDateTime.of(2010, 10, 10, 10, 10, 10);
        return new PortionDto(id, lettersIds, timeStamp);
    }
}
