package rus.Ilya.Producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import rus.Ilya.Owners.Owner;
import rus.Ilya.Owners.OwnerDto;
import rus.Ilya.ResponseListeners.GetAllResponseListener;
import rus.Ilya.ResponseListeners.GetRequestResponseListener;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Component
public class Producer {

    @Value("${owner_topic.create}")
    private String createTopicName;

    @Value("${owner_topic.update}")
    private String updateTopicName;

    @Value("${owner_topic.deleteById}")
    private String deleteByIdTopicName;

    @Value("${owner_topic.deleteByEntity}")
    private String deleteByEntityTopicName;

    @Value("${owner_topic.deleteByAll}")
    private String deleteByAllTopicName;

    @Value("${owner_topic.getById}")
    private String getByIdTopicName;

    @Value("${owner_topic.getAll}")
    private String getAllTopicName;
    private final GetAllResponseListener getAllRequestResponseListener;

    private final GetRequestResponseListener getRequestResponseListener;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public Producer(GetAllResponseListener getAllRequestResponseListener,
                    GetRequestResponseListener getRequestResponseListener,
                    KafkaTemplate<String, Object> kafkaTemplate) {
        this.getAllRequestResponseListener = getAllRequestResponseListener;
        this.getRequestResponseListener = getRequestResponseListener;
        this.kafkaTemplate = kafkaTemplate;
    }

    public ResponseEntity<?> sendCreateToKafka(Owner owner) {
            kafkaTemplate.send(createTopicName,owner);
            return ResponseEntity.status(HttpStatus.OK).body("Message send to kafka");
    }

    public ResponseEntity<?> sendDeleteByIdToKafka(long id) {
        kafkaTemplate.send(deleteByIdTopicName,id);
        return ResponseEntity.status(HttpStatus.OK).body("Message send to kafka");
    }

    public ResponseEntity<?> sendDeleteByEntityToKafka(Owner owner) {
        kafkaTemplate.send(deleteByEntityTopicName,owner);
        return ResponseEntity.status(HttpStatus.OK).body("Message send to kafka");
    }

    public ResponseEntity<?> sendUpdateToKafka(Owner owner) {
        kafkaTemplate.send(updateTopicName,owner);
        return ResponseEntity.status(HttpStatus.OK).body("Message send to kafka");
    }

    public ResponseEntity<?> sendGetByIdToKafka(long id) throws ExecutionException, InterruptedException, TimeoutException {
        String correlationId = UUID.randomUUID().toString();

        kafkaTemplate.send(getByIdTopicName, correlationId, id);

        OwnerDto response = getRequestResponseListener.getResponse(correlationId, OwnerDto.class);

        if (response==null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> sendGetAllToKafka() throws ExecutionException, InterruptedException, TimeoutException {
        String correlationId = UUID.randomUUID().toString();

        kafkaTemplate.send(getAllTopicName, correlationId,"all");



        var response = getAllRequestResponseListener.getResponse(correlationId,  List.class);

        if (response==null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(response);

    }


    public ResponseEntity<?> sendDeleteAllToKafka() {
        kafkaTemplate.send(deleteByAllTopicName,"delete all");
        return ResponseEntity.status(HttpStatus.OK).body("Message send to kafka");
    }

}
