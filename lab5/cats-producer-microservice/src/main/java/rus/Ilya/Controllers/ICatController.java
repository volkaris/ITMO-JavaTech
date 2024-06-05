package rus.Ilya.Controllers;

import org.springframework.http.ResponseEntity;
import rus.Ilya.Cats.Cat;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface ICatController {

    public ResponseEntity<?> addFriend(long firstFriendId, long secondFriendId);
    public ResponseEntity<?> save(Cat entity);
    public ResponseEntity<?> deleteById(long id);
    public ResponseEntity<?> deleteByEntity(Cat entity);
    public ResponseEntity<?> deleteAll();
    public ResponseEntity<?> update(Cat entity);
    public ResponseEntity<?> getById(long id) throws ExecutionException, InterruptedException, TimeoutException;
    public ResponseEntity<?> getAll() throws ExecutionException, InterruptedException, TimeoutException;
}
