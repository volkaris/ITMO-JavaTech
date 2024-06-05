package Service.Owners;

import Ports.Owners.IOwnerRepository;
import Service.Security.Entities.AuthenticationResponse;
import Service.Security.JWT.Services.IJwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerService implements IOwnerService, UserDetailsService {

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
    public ResponseEntity<AuthenticationResponse> save(Owner owner) {

        if (repository.existsById(owner.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        var jwt = jwtService.generateJwt(owner);


        var savealbeOwner = new Owner(owner.getId(),
                owner.getName(),
                passwordEncoder.encode(owner.getPassword()),
                owner.getRole(),
                owner.getBirthday());

        var result = repository.save(savealbeOwner);

        return ResponseEntity.ok( new AuthenticationResponse(new OwnerDto(result),jwt));

    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
         var owner = repository.getById(Long.parseLong(id));
        if (owner.isPresent()) {
            return   owner.get();
        } else {
            throw new UsernameNotFoundException(id);
        }
    }

    @Override
    public ResponseEntity<Void> deleteById(long id) {

        var entity = repository.findById(id);

        if (entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        repository.delete(entity.get());

        return ResponseEntity.noContent().build();
    }


    @Override
    public ResponseEntity<Void> deleteByEntity(Owner owner) {

        if (!repository.existsById(owner.getId())) {
            return ResponseEntity.notFound().build();
        }

        repository.delete(owner);

        return ResponseEntity.noContent().build();

    }

    @Override
    public ResponseEntity<Void> deleteAll() {

        repository.deleteAll();

        return ResponseEntity.noContent().build();

    }

    @Override
    public ResponseEntity<Void> update(Owner owner) {

        if (!repository.existsById(owner.getId())) {
            return ResponseEntity.notFound().build();
        }

        repository.save(owner);

        return ResponseEntity.noContent().build();

    }

    @Override
    public ResponseEntity<OwnerDto> getById(long id) {

        var result = repository.findById(id);

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new OwnerDto(result.get()));
    }

    @Override
    public ResponseEntity<List<OwnerDto>> getAll() {

        Iterable<Owner> owners = repository.findAll();
        List<OwnerDto> dtos = new ArrayList<>();

        for (var i : owners) {
            dtos.add(new OwnerDto(i));
        }

        return ResponseEntity.ok(dtos);
    }
}
