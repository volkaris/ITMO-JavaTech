package Service.Cats;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICatService {
    public ResponseEntity<CatDto> save(Cat cat);

    public ResponseEntity<Void> deleteById(long id);

    public ResponseEntity<Void> deleteByEntity(Cat cat);

    public ResponseEntity<Void> deleteAll();

    public ResponseEntity<Void> update(Cat cat);

    public ResponseEntity<CatDto> getById(long id);

   public ResponseEntity<Void> addFriend( long firstFriendId, long secondFriendId) ;


    public ResponseEntity<List<CatDto>> getAll();

}
