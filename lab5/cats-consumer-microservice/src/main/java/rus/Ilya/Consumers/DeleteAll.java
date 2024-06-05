package rus.Ilya.Consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import rus.Ilya.Services.ICatService;

@Component
public class DeleteAll implements IConsumer {
    private final ICatService ownerService;

    @Autowired
    public DeleteAll(ICatService ownerService) {
        this.ownerService = ownerService;
    }

    @Override
    @KafkaListener(topics = "cats.deleteByAll", groupId = "default")
    public ResponseEntity<?> consumeMessage(String message) {
        return ownerService.deleteAll();
    }
}
