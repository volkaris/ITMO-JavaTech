package rus.Ilya.Consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import rus.Ilya.Services.ICatService;

@Component
public class DeleteByIdConsumer implements IConsumer{

    private final ICatService ownerService;

    @Autowired
    public DeleteByIdConsumer(ICatService ownerService) {
        this.ownerService = ownerService;
    }

    @Override
    @KafkaListener(topics = "cats.deleteById", groupId = "default")
    public ResponseEntity<?> consumeMessage(String message) {
        return ownerService.deleteById(Integer.parseInt(message));
    }
}
