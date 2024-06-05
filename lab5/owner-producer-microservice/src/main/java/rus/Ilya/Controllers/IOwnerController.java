package rus.Ilya.Controllers;

import org.springframework.http.ResponseEntity;
import rus.Ilya.Owners.Owner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface IOwnerController {
    public ResponseEntity<?> create(Owner owner);
    public ResponseEntity<?> deleteById(long id);
    public ResponseEntity<?> deleteByEntity(Owner owner);
    public ResponseEntity<?> deleteAll();
    public ResponseEntity<?> update(Owner owner);
    public ResponseEntity<?> getById(long id) throws ExecutionException, InterruptedException, TimeoutException;
    public ResponseEntity<?> getAll() throws ExecutionException, InterruptedException, TimeoutException;
}