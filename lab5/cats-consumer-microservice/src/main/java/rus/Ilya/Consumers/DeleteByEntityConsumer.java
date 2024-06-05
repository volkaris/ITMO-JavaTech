package rus.Ilya.Consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import rus.Ilya.Cats.CatDto;
import rus.Ilya.Services.ICatService;

@Component
public class DeleteByEntityConsumer implements IConsumer {

    private final ObjectMapper objectMapper;


    private final ICatService ownerService;

    @Autowired
    public DeleteByEntityConsumer(ObjectMapper objectMapper, ICatService ownerService) {
        this.objectMapper = objectMapper;
        this.ownerService = ownerService;
    }

    @Override
    @KafkaListener(topics = "cats.deleteByEntity", groupId = "default")
    public ResponseEntity<?> consumeMessage(String message) throws JsonProcessingException {
        var catDto = objectMapper.readValue(message, CatDto.class);
        ownerService.deleteByEntity(catDto);
        return ResponseEntity.ok().build();
    }
}
