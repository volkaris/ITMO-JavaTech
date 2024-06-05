package rus.Ilya.ResponseListeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class GetAllResponseListener {
    private final ConcurrentHashMap<String, CompletableFuture<String>> responseFutures = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper;

    private final String topicName = "owners.responseGetAll";
    @Autowired
    public GetAllResponseListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = topicName, groupId = "response-group")
    public void listenForResponse(ConsumerRecord<String, String> record) {
        String correlationId = record.key();
        String response = record.value();


        CompletableFuture<String> future = responseFutures.remove(correlationId);
        if (future != null) {
            future.complete(response);
        }
    }



    public <T> T getResponse(String correlationId, Class<T> valueType) throws InterruptedException, ExecutionException, TimeoutException {
        CompletableFuture<String> future = new CompletableFuture<>();
        responseFutures.put(correlationId, future);

        String jsonResponse = future.get(30, TimeUnit.SECONDS);

        try {
            var result=objectMapper.readValue(jsonResponse, valueType);

            return result;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON response", e);
        }
    }
}
