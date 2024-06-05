package Service.Cats;

import org.springframework.http.ResponseEntity;

public interface ICatService {
    public ResponseEntity<?> save(Cat cat);

    public ResponseEntity<?> deleteById(long id);

    public ResponseEntity<?> deleteByEntity(Cat cat);

    public ResponseEntity<?> deleteAll();

    public ResponseEntity<?> update(Cat cat);

    public ResponseEntity<?> getById(long id);

   public ResponseEntity<?> addFriend( long firstFriendId, long secondFriendId) ;

    public ResponseEntity<?> getAll();

}
