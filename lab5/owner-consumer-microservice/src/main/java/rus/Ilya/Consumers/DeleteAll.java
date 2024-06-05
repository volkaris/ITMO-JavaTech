package rus.Ilya.Consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import rus.Ilya.Services.IOwnerService;

@Component
public class DeleteAll implements IConsumer {
    private final IOwnerService ownerService;

    @Autowired
    public DeleteAll(IOwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Override
    @KafkaListener(topics = "owners.deleteByAll", groupId = "default")
    public ResponseEntity<?> consumeMessage(String message) {
        return ownerService.deleteAll();
    }
}
