package rus.Ilya.Consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import rus.Ilya.Services.IOwnerService;

@Component
public class GetByIdConsumer  {

    private final IOwnerService ownerService;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public GetByIdConsumer(IOwnerService ownerService, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.ownerService = ownerService;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "owners.getById", groupId = "default")
    public void consumeMessage(ConsumerRecord<String, String> record) {

        String correlationId = record.key();
        String message = record.value();

        try {

            var result = ownerService.getById(Integer.parseInt(message));

            String response = objectMapper.writeValueAsString(result.getBody());

            System.out.println("result is " + result);
            System.out.println("responce is " + response);

            kafkaTemplate.send("owners.response", correlationId, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
