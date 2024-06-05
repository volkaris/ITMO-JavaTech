package rus.Ilya.Services;

import org.springframework.http.ResponseEntity;
import rus.Ilya.Cats.Cat;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface ICatService {

    public ResponseEntity<?> save(Cat cat);

    public ResponseEntity<?> deleteById(long id);

    public ResponseEntity<?> deleteByEntity(Cat cat);

    public ResponseEntity<?> deleteAll();

    public ResponseEntity<?> update(Cat cat);

    public ResponseEntity<?> getById(long id) throws ExecutionException, InterruptedException, TimeoutException;

    public ResponseEntity<?> addFriend(long firstFriendId, long secondFriendId);

    public ResponseEntity<?> getAll() throws ExecutionException, InterruptedException, TimeoutException;
}
