package rus.Ilya.Consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import rus.Ilya.Services.IOwnerService;

@Component
public class GetAllConsumer  {

    private final IOwnerService ownerService;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    @Autowired
    public GetAllConsumer(IOwnerService ownerService, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.ownerService = ownerService;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }


    @KafkaListener(topics = "owners.getAll", groupId = "default")
    public void consumeMessage(ConsumerRecord<String, String> record) {

        String correlationId = record.key();
        try {

            var result = ownerService.getAll();

            String response = objectMapper.writeValueAsString(result.getBody());

            System.out.println("result is " + result);
            System.out.println("responce is " + response);

            kafkaTemplate.send("owners.responseGetAll", correlationId, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
