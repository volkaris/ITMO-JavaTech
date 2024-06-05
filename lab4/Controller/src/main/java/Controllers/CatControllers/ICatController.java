package Controllers.CatControllers;

import Service.Cats.Cat;
import org.springframework.http.ResponseEntity;

public interface ICatController {
    public ResponseEntity<?> addFriend(long firstFriendId, long secondFriendId);
    public ResponseEntity<?> save(Cat entity);
    public ResponseEntity<?> deleteById(long id);
    public ResponseEntity<?> deleteByEntity(Cat entity);
    public ResponseEntity<?> deleteAll();
    public ResponseEntity<?> update(Cat entity);
    public ResponseEntity<?> getById(long id);
    public ResponseEntity<?> getAll();
}
