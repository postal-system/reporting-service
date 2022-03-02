package io.aimc.reportingservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.aimc.reportingservice.dto.PortionDto;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@EnableKafka
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092"})
public class IntegrationPortionTest extends AbstractControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    private final UUID id = UUID.randomUUID();
    private final List<UUID> lettersIds = List.of(UUID.randomUUID(), UUID.randomUUID());
    private final LocalDateTime timeStamp = LocalDateTime.of(2010, 10, 10, 10, 10, 10);

    @Autowired
    EmbeddedKafkaBroker embeddedKafkaBroker;

    @Test
    public void shouldReturnPortionDtoFromRestIfWriteCreatePortionDtoToKafka() throws Exception {
        Producer<String, PortionDto> producer = configureProducer();
        producer.send(new ProducerRecord<>("portions", new PortionDto(id, lettersIds, timeStamp)));
    }

    private Producer<String, PortionDto> configureProducer() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        return new DefaultKafkaProducerFactory<String, PortionDto>(props).createProducer();
    }
}
