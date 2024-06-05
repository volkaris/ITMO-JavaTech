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
public class DeleteByEntityConsumer implements IConsumer {

    private final ObjectMapper objectMapper;


    private final IOwnerService ownerService;

    @Autowired
    public DeleteByEntityConsumer(ObjectMapper objectMapper, IOwnerService ownerService) {
        this.objectMapper = objectMapper;
        this.ownerService = ownerService;
    }

    @Override
    @KafkaListener(topics = "owners.deleteByEntity", groupId = "default")
    public ResponseEntity<?> consumeMessage(String message) throws JsonProcessingException {
        OwnerDto catDto = objectMapper.readValue(message, OwnerDto.class);
        ownerService.deleteByEntity(catDto);
        return ResponseEntity.ok().build();
    }
}
