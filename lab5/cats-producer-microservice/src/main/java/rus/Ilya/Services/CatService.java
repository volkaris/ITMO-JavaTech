package rus.Ilya.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rus.Ilya.Cats.Cat;
import rus.Ilya.Producers.Producer;
import rus.Ilya.Roles.Role;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Service
public class CatService implements ICatService {

    private final Producer producer;

    @Autowired
    public CatService(Producer producer) {
        this.producer = producer;
    }

    @Override
    public ResponseEntity<?> save(Cat cat) {

        return producer.sendCreateToKafka(cat);
    }

    @Override
    public ResponseEntity<?> deleteById(long id) {
        return producer.sendDeleteByIdToKafka(id);
    }

    @Override
    public ResponseEntity<?> deleteByEntity(Cat cat) {
        return producer.sendDeleteByEntityToKafka(cat);
    }

    @Override
    public ResponseEntity<?> update(Cat cat) {
        return producer.sendUpdateToKafka(cat);
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

    @Override
    public ResponseEntity<?> addFriend(long firstFriendId, long secondFriendId) {
        return producer.sendAddFriendToKafka(firstFriendId,secondFriendId);
    }

}
