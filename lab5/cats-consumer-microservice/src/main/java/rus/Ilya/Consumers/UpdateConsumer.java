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
public class UpdateConsumer implements IConsumer {

    private final ICatService catService;
    private final ObjectMapper objectMapper;

    @Autowired
    public UpdateConsumer(ICatService catService, ObjectMapper objectMapper) {
        this.catService = catService;
        this.objectMapper = objectMapper;
    }

    @Override
    @KafkaListener(topics = "cats.update", groupId = "default")
    public ResponseEntity<?> consumeMessage(String message) throws JsonProcessingException {
        var catDto = objectMapper.readValue(message, CatDto.class);
        catService.update(catDto);
        return ResponseEntity.ok().build();
    }
}
