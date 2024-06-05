package rus.Ilya.Services;

import org.springframework.http.ResponseEntity;
import rus.Ilya.Cats.CatDto;


public interface ICatService {

    public ResponseEntity<?> save(CatDto owner);

    public ResponseEntity<?> deleteById(long id);

    public ResponseEntity<?> deleteByEntity(CatDto owner);

    public ResponseEntity<?> update(CatDto owner);

    public ResponseEntity<?> getById(long id);

    public ResponseEntity<?> getAll();

    public ResponseEntity<?> deleteAll();

    public ResponseEntity<?> addFriend(long firstFriendId, long secondFriendId);
}
