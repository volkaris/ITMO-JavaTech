package rus.Ilya.Consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

public interface IConsumer {

    public ResponseEntity<?> consumeMessage(String message) throws JsonProcessingException;
}
