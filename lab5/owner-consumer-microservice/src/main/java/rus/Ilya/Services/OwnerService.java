package rus.Ilya.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rus.Ilya.Entities.AuthenticationResponse;
import rus.Ilya.JWT.Services.IJwtService;
import rus.Ilya.Owners.Owner;
import rus.Ilya.Owners.OwnerDto;
import rus.Ilya.Repositories.IOwnerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerService implements IOwnerService {

    private final IOwnerRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final IJwtService jwtService;

    @Autowired
    public OwnerService(IOwnerRepository repository,
                        PasswordEncoder passwordEncoder,
                        IJwtService jwtService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public ResponseEntity<AuthenticationResponse> save(OwnerDto owner) {

        if (repository.existsById(owner.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        var jwt = jwtService.generateJwt(new Owner(owner));


        var savealbeOwner = new Owner(owner.getId(),
                owner.getName(),
                passwordEncoder.encode(owner.getPassword()),
                owner.getRole(),
                owner.getBirthday());

        var result = repository.save(savealbeOwner);

        return ResponseEntity.ok(new AuthenticationResponse(new OwnerDto(result),jwt));
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
    public ResponseEntity<?> deleteByEntity(OwnerDto owner) {

        if (!repository.existsById(owner.getId())) {
            return ResponseEntity.notFound().build();
        }

        repository.delete(new Owner(owner));

        return ResponseEntity.noContent().build();

    }

    @Override
    public ResponseEntity<?> deleteAll() {

        repository.deleteAll();

        return ResponseEntity.noContent().build();

    }

    @Override
    public ResponseEntity<?> update(OwnerDto owner) {


        if (!repository.existsById(owner.getId())) {
            return ResponseEntity.notFound().build();
        }


        var savealbeOwner = new Owner(owner.getId(),
                owner.getName(),
                passwordEncoder.encode(owner.getPassword()),
                owner.getRole(),
                owner.getBirthday());

         repository.save(savealbeOwner);

        return ResponseEntity.noContent().build();

    }

    @Override
    public ResponseEntity<?> getById(long id) {

        var result = repository.findById(id);

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new OwnerDto(result.get()));
    }

    @Override
    public ResponseEntity<?> getAll() {

        Iterable<Owner> owners = repository.findAll();
        List<OwnerDto> dtos = new ArrayList<>();

        for (var i : owners) {
            dtos.add(new OwnerDto(i));
        }

        return ResponseEntity.ok(dtos);
    }
}
