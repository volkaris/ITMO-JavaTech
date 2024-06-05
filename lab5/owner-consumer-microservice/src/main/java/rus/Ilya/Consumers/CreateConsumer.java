package rus.Ilya.Consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import rus.Ilya.Owners.OwnerDto;
import rus.Ilya.Services.IOwnerService;

@Component
@EnableKafka
public class CreateConsumer implements IConsumer {
    private final ObjectMapper objectMapper;
    private final IOwnerService ownerService;

    @Autowired
    public CreateConsumer(ObjectMapper objectMapper, IOwnerService ownerService) {
        this.objectMapper = objectMapper;
        this.ownerService = ownerService;
    }

    @KafkaListener(topics = "owners.create", groupId = "default")
    public ResponseEntity<?> consumeMessage(String message) throws JsonProcessingException {

        OwnerDto catDto = objectMapper.readValue(message, OwnerDto.class);
        ownerService.save(catDto);
        return ResponseEntity.ok().build();
    }
}
