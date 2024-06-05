package Service.Cats;

import Ports.Cats.ICatRepository;
import Roles.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity<?> save(Cat cat) {

        if (!(getCurrentUserId().equals(cat.getOwnerId())) && currentUserIsUser()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User isn't allowed to add cats to other users");
        }

        if (repository.existsById(cat.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("cat with this ID already exists");
        }

        var result = repository.save(cat);

        return ResponseEntity.ok(new CatDto(result));
    }


    @Override
    public ResponseEntity<?> deleteById(long id) {

        var entity = repository.getCatById(id);

        if (entity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("cat with id " + id + " doesn't exist");
        }

        if (!(getCurrentUserId().equals(entity.get().getOwnerId())) && currentUserIsUser()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User isn't allowed to delete cats from other users");
        }


        repository.delete(entity.get());

        return ResponseEntity.noContent().build();
    }


    @Override
    public ResponseEntity<?> deleteByEntity(Cat cat) {
        return deleteById(cat.getId());
    }


    @Override
    public ResponseEntity<?> deleteAll() {


        repository.deleteAll();

        return ResponseEntity.noContent().build();

    }

    @Override
    public ResponseEntity<?> update(Cat cat) {

        var entity = repository.getCatById(cat.getId());

        if (entity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("cat with id " + cat.getId() + " doesn't exist");
        }

        if (!(getCurrentUserId().equals(entity.get().getOwnerId())) && currentUserIsUser()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User isn't allowed to update cats from other users");
        }


        repository.save(cat);

        return ResponseEntity.noContent().build();

    }

    @Override
    public ResponseEntity<?> getById(long id) {


        var entity = repository.getCatById(id);

        if (entity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("cat with id " + id + " doesn't exist");
        }

        if (!(getCurrentUserId().equals(entity.get().getOwnerId())) && currentUserIsUser()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User isn't allowed to get information about other's cats");
        }


        return ResponseEntity.ok(new CatDto(entity.get()));
    }

    @Override
    public ResponseEntity<?> addFriend(long firstFriendId, long secondFriendId) {

        if (!(getCurrentUserId().equals(firstFriendId)) && currentUserIsUser()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User isn't allowed to add cats to other users");
        }


        if (!repository.existsById(firstFriendId) || !repository.existsById(secondFriendId)) {
            return ResponseEntity.notFound().build();
        }

        repository.addFriend(firstFriendId, secondFriendId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> getAll() {

        Iterable<Cat> cats = repository.findAll();
        List<CatDto> dtos = new ArrayList<>();

        for (var i : cats) {
            dtos.add(new CatDto(i));
        }

        return ResponseEntity.ok(dtos);

    }


    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Long.valueOf(authentication.getName());
    }

    private List<GrantedAuthority> getCurrentUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (List<GrantedAuthority>) authentication.getAuthorities();
    }

    private boolean currentUserIsAdmin() {

        var authorities = getCurrentUserRole();
        for (var i : authorities) {
            if (i.getAuthority().equals(Role.Admin.name())) {
                return true;
            }
        }
        return false;
    }

    private boolean currentUserIsUser() {

        var authorities = getCurrentUserRole();
        for (var i : authorities) {
            if (i.getAuthority().equals(Role.User.name())) {
                return true;
            }
        }
        return false;
    }
}
