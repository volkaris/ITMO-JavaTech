package Controllers.CatControllers;

import Service.Cats.Cat;
import Service.Cats.CatDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICatController {

    public ResponseEntity<Void> addFriend(long firstFriendId, long secondFriendId);
    public ResponseEntity<CatDto> save(Cat entity);
    public ResponseEntity<Void> deleteById(long id);
    public ResponseEntity<Void> deleteByEntity(Cat entity);
    public ResponseEntity<Void> deleteAll();
    public ResponseEntity<Void> update(Cat entity);
    public ResponseEntity<CatDto> getById(long id);
    public ResponseEntity<List<CatDto>> getAll();

}
