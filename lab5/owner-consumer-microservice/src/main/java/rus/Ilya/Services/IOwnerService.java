package rus.Ilya.Services;

import org.springframework.http.ResponseEntity;

import rus.Ilya.Entities.AuthenticationResponse;
import rus.Ilya.Owners.OwnerDto;

public interface IOwnerService {

    public ResponseEntity<AuthenticationResponse> save(OwnerDto owner);

    public ResponseEntity<?> deleteById(long id);

    public ResponseEntity<?> deleteByEntity(OwnerDto owner);

    public ResponseEntity<?> update(OwnerDto owner);

    public ResponseEntity<?> getById(long id);

    public ResponseEntity<?> getAll();

    public ResponseEntity<?> deleteAll();

}
