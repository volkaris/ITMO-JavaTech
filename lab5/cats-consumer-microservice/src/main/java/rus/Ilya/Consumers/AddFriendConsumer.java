package rus.Ilya.Consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import rus.Ilya.Services.ICatService;

@Component
@EnableKafka
public class AddFriendConsumer implements IConsumer {
    private final ICatService ownerService;


    @Autowired
    public AddFriendConsumer(ICatService ownerService) {
        this.ownerService = ownerService;
    }

    @Override
    @KafkaListener(topics = "cats.addFriend", groupId = "default")
    public ResponseEntity<?> consumeMessage(String message) throws JsonProcessingException {

        message = message.replace("\"", "");

        System.out.println(message);
        var parsedMessage = message.split(" ");

        long firstFriendId = Long.parseLong(parsedMessage[0]);

        long secondFriendId = Long.parseLong(parsedMessage[1]);
        return ownerService.addFriend(firstFriendId, secondFriendId);
    }
}
