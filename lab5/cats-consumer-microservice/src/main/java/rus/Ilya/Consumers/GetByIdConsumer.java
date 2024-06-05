package rus.Ilya.Consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import rus.Ilya.Services.ICatService;

@Component
public class GetByIdConsumer  {

    private final ICatService catService;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    @Autowired
    public GetByIdConsumer(ICatService catService, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.catService = catService;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }


    @KafkaListener(topics = "cats.getById", groupId = "default")
    public void consumeMessage(ConsumerRecord<String, String> record) {

        String correlationId = record.key();
        String message = record.value();

        try {

            var result = catService.getById(Integer.parseInt(message));

            String response = objectMapper.writeValueAsString(result.getBody());

            System.out.println("result is " + result);
            System.out.println("responce is " + response);

            kafkaTemplate.send("cats.response", correlationId, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
