package rus.Ilya.Consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import rus.Ilya.Services.ICatService;

@Component
public class GetAllConsumer  {

    private final ICatService ownerService;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public GetAllConsumer(ICatService ownerService, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.ownerService = ownerService;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }


    @KafkaListener(topics = "cats.getAll", groupId = "default")
    public void consumeMessage(ConsumerRecord<String, String> record) {
        String correlationId = record.key();
        try {

            var result = ownerService.getAll();

            String response = objectMapper.writeValueAsString(result.getBody());

            kafkaTemplate.send("cats.responseGetAll", correlationId, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
