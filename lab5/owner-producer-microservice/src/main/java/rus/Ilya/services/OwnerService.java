package rus.Ilya.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rus.Ilya.Owners.Owner;
import rus.Ilya.Producers.Producer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


@Service
public class OwnerService implements IOwnerService {

    private final Producer producer;

    @Autowired
    public OwnerService(Producer producer) {
        this.producer = producer;
    }

    @Override
    public ResponseEntity<?> save(Owner owner) {

        return producer.sendCreateToKafka(owner);
    }

    @Override
    public ResponseEntity<?> deleteById(long id) {
        return producer.sendDeleteByIdToKafka(id);
    }

    @Override
    public ResponseEntity<?> deleteByEntity(Owner owner) {
        return producer.sendDeleteByEntityToKafka(owner);
    }

    @Override
    public ResponseEntity<?> update(Owner owner) {
        return producer.sendUpdateToKafka(owner);
    }

    @Override
    public ResponseEntity<?> getById(long id) throws ExecutionException, InterruptedException, TimeoutException {
        return producer.sendGetByIdToKafka(id);
    }

    @Override
    public ResponseEntity<?> getAll() throws ExecutionException, InterruptedException, TimeoutException {
        return producer.sendGetAllToKafka();
    }

    @Override
    public ResponseEntity<?> deleteAll() {
        return producer.sendDeleteAllToKafka();
    }

}