package rus.Ilya.Consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import rus.Ilya.Cats.CatDto;
import rus.Ilya.Services.ICatService;

@Component
@EnableKafka
public class CreateConsumer implements IConsumer {
    private final ObjectMapper objectMapper;
    private final ICatService catService;

    @Autowired
    public CreateConsumer(ObjectMapper objectMapper, ICatService catService) {
        this.objectMapper = objectMapper;
        this.catService = catService;
    }

    @KafkaListener(topics = "cats.create", groupId = "default")
    public ResponseEntity<?> consumeMessage(String message) throws JsonProcessingException {


        CatDto catDto = objectMapper.readValue(message, CatDto.class);
        catService.save(catDto);


        return ResponseEntity.ok().build();
    }
}
