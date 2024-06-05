package rus.Ilya.Consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import rus.Ilya.Owners.OwnerDto;
import rus.Ilya.Services.IOwnerService;

@Component
public class UpdateConsumer implements IConsumer {

    private final IOwnerService ownerService;
    private final ObjectMapper objectMapper;

    @Autowired
    public UpdateConsumer(IOwnerService ownerService, ObjectMapper objectMapper) {
        this.ownerService = ownerService;
        this.objectMapper = objectMapper;
    }

    @Override
    @KafkaListener(topics = "owners.update", groupId = "default")
    public ResponseEntity<?> consumeMessage(String message) throws JsonProcessingException {
        OwnerDto catDto = objectMapper.readValue(message, OwnerDto.class);
        ownerService.update(catDto);
        return ResponseEntity.ok().build();
    }
}
