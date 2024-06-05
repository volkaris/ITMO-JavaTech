package rus.Ilya.Consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import rus.Ilya.Services.IOwnerService;

@Component
public class DeleteByIdConsumer implements IConsumer{

    private final IOwnerService ownerService;

    @Autowired
    public DeleteByIdConsumer(IOwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Override
    @KafkaListener(topics = "owners.deleteById", groupId = "default")
    public ResponseEntity<?> consumeMessage(String message) {
        return ownerService.deleteById(Integer.parseInt(message));
    }
}
