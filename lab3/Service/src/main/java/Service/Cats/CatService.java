package Service.Cats;

import Ports.Cats.ICatRepository;
import Service.Owners.Owner;
import Service.Owners.OwnerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatService implements ICatService {

    ICatRepository repository;

    @Autowired
    public CatService(ICatRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<CatDto> save(Cat cat) {

        if (repository.existsById(cat.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        var result = repository.save(cat);

        return ResponseEntity.ok(new CatDto(result));


    }

    @Override
    public ResponseEntity<Void> deleteById(long id) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        var entity = repository.getById(id);

        repository.delete(entity);

        return ResponseEntity.noContent().build();
    }


    @Override
    public ResponseEntity<Void> deleteByEntity(Cat cat) {


        if (!repository.existsById(cat.getId())) {
            return ResponseEntity.notFound().build();
        }

        repository.delete(cat);

        return ResponseEntity.noContent().build();

    }

    @Override
    public ResponseEntity<Void> deleteAll() {


        repository.deleteAll();

        return ResponseEntity.noContent().build();

    }

    @Override
    public ResponseEntity<Void> update(Cat cat) {

        if (!repository.existsById(cat.getId())) {
            return ResponseEntity.notFound().build();
        }

        repository.save(cat);

        return ResponseEntity.noContent().build();

    }

    @Override
    public ResponseEntity<CatDto> getById(long id) {

        var result = repository.findById(id);

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new CatDto(result.get()));
    }

    @Override
    public ResponseEntity<Void> addFriend(long firstFriendId, long secondFriendId) {
        if (!repository.existsById(firstFriendId) || !repository.existsById(secondFriendId)) {
            return ResponseEntity.notFound().build();
        }

        repository.addFriend(firstFriendId, secondFriendId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<CatDto>> getAll() {

        Iterable<Cat> cats = repository.findAll();
        List<CatDto> dtos = new ArrayList<>();

        for (var i : cats) {
            dtos.add(new CatDto(i));
        }

        return ResponseEntity.ok(dtos);

    }
}
