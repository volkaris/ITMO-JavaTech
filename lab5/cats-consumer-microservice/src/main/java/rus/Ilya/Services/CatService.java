package rus.Ilya.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rus.Ilya.Cats.Cat;
import rus.Ilya.Cats.CatDto;

import rus.Ilya.Repositories.ICatRepository;
import rus.Ilya.Roles.Role;
import rus.Ilya.UserCheckers.UserChecker;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatService implements ICatService {


    private final ICatRepository repository;

    @Autowired
    public CatService(ICatRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<?> save(CatDto catDto) {

        if (repository.existsById(catDto.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        var result = repository.save(new Cat(catDto));
        System.out.println(result);
        return ResponseEntity.ok(catDto);

    }

    @Override
    public ResponseEntity<?> deleteById(long id) {

        var entity = repository.findById(id);

        if (entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        repository.delete(entity.get());

        return ResponseEntity.noContent().build();
    }


    @Override
    public ResponseEntity<?> deleteByEntity(CatDto catDto) {

        if (!repository.existsById(catDto.getId())) {
            return ResponseEntity.notFound().build();
        }

        repository.delete(new Cat(catDto));

        return ResponseEntity.noContent().build();

    }

    @Override
    public ResponseEntity<?> deleteAll() {

        repository.deleteAll();

        return ResponseEntity.noContent().build();

    }

    @Override
    public ResponseEntity<?> addFriend(long firstFriendId, long secondFriendId) {
        repository.addFriend(firstFriendId,secondFriendId);
        repository.addFriend(secondFriendId,firstFriendId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> update(CatDto catDto) {

        if (!repository.existsById(catDto.getId())) {
            return ResponseEntity.notFound().build();
        }

        repository.save(new Cat(catDto));

        return ResponseEntity.noContent().build();

    }

    @Override
    public ResponseEntity<?> getById(long id) {

        var result = repository.findById(id);

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new CatDto(result.get()));
    }

    @Override
    public ResponseEntity<?> getAll() {

        Iterable<Cat> owners = repository.findAll();
        List<CatDto> dtos = new ArrayList<>();

        for (var i : owners) {
            dtos.add(new CatDto(i));
        }

        return ResponseEntity.ok(dtos);
    }

}
