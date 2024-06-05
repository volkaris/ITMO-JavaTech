package Controllers.OwnerControllers;

import Service.Owners.Owner;
import Service.Owners.OwnerDto;
import Service.Security.Entities.AuthenticationRequest;
import Service.Security.Entities.AuthenticationResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IOwnerController {
    public ResponseEntity<AuthenticationResponse> create(Owner entity);
    public ResponseEntity<Void> deleteById(long id);
    public ResponseEntity<Void> deleteByEntity(Owner entity);
    public ResponseEntity<Void> deleteAll();
    public ResponseEntity<Void> update(Owner entity);
    public ResponseEntity<OwnerDto> getById(long id);
    public ResponseEntity<List<OwnerDto>> getAll();

    public ResponseEntity<AuthenticationResponse> authorize(AuthenticationRequest request);
}
