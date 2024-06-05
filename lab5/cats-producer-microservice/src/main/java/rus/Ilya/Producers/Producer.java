package rus.Ilya.Producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import rus.Ilya.Cats.Cat;
import rus.Ilya.Cats.CatDto;
import rus.Ilya.ResponseListeners.GetAllResponseListener;
import rus.Ilya.ResponseListeners.GetRequestResponseListener;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Component
public class Producer {

    @Value("${cats_topic.create}")
    private String createTopicName;

    @Value("${cats_topic.update}")
    private String updateTopicName;

    @Value("${cats_topic.deleteById}")
    private String deleteByIdTopicName;

    @Value("${cats_topic.deleteByEntity}")
    private String deleteByEntityTopicName;

    @Value("${cats_topic.deleteByAll}")
    private String deleteByAllTopicName;

    @Value("${cats_topic.getById}")
    private String getByIdTopicName;

    @Value("${cats_topic.getAll}")
    private String getAllTopicName;

    @Value("${cats_topic.addFriend}")
    private String addFriendTopicName;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final GetAllResponseListener getAllRequestResponseListener;

    private final GetRequestResponseListener getRequestResponseListener;

    @Autowired
    public Producer(KafkaTemplate<String, Object> kafkaTemplate,
                    GetAllResponseListener getAllRequestResponseListener,
                    GetRequestResponseListener getRequestResponseListener) {
        this.kafkaTemplate = kafkaTemplate;
        this.getAllRequestResponseListener = getAllRequestResponseListener;
        this.getRequestResponseListener = getRequestResponseListener;
    }

    public ResponseEntity<?> sendCreateToKafka(Cat owner) {
        kafkaTemplate.send(createTopicName,owner);
        return ResponseEntity.status(HttpStatus.OK).body("Message send to kafka");
    }

    public ResponseEntity<?> sendAddFriendToKafka(long firstFriendId, long secondFriendId) {
        String str=firstFriendId+" "+secondFriendId;
        kafkaTemplate.send(addFriendTopicName,str);
        return ResponseEntity.status(HttpStatus.OK).body("Message send to kafka");
    }


    public ResponseEntity<?> sendDeleteByIdToKafka(long id) {
        kafkaTemplate.send(deleteByIdTopicName,id);
        return ResponseEntity.status(HttpStatus.OK).body("Message send to kafka");
    }

    public ResponseEntity<?> sendDeleteByEntityToKafka(Cat owner) {
        kafkaTemplate.send(deleteByEntityTopicName,owner);
        return ResponseEntity.status(HttpStatus.OK).body("Message send to kafka");
    }

    public ResponseEntity<?> sendUpdateToKafka(Cat owner) {
        kafkaTemplate.send(updateTopicName,owner);
        return ResponseEntity.status(HttpStatus.OK).body("Message send to kafka");
    }

    public ResponseEntity<?> sendGetByIdToKafka(long id) throws ExecutionException, InterruptedException, TimeoutException {
        String correlationId = UUID.randomUUID().toString();

        kafkaTemplate.send(getByIdTopicName, correlationId, id);

        CatDto response = getRequestResponseListener.getResponse(correlationId, CatDto.class);

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
