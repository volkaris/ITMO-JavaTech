package Service.Owners;

import Service.Security.Entities.AuthenticationResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IOwnerService  {


    public ResponseEntity<AuthenticationResponse> save(Owner owner);

    public ResponseEntity<Void> deleteById(long id);

    public ResponseEntity<Void> deleteByEntity(Owner owner);

    public ResponseEntity<Void> update(Owner owner);

    public ResponseEntity<OwnerDto> getById(long id);

    public ResponseEntity<List<OwnerDto>> getAll();

    public ResponseEntity<Void> deleteAll();
}
