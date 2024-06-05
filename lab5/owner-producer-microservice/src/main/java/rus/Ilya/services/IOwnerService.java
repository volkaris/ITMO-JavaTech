package rus.Ilya.services;


import org.springframework.http.ResponseEntity;
import rus.Ilya.Owners.Owner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface IOwnerService {

    public ResponseEntity<?> save(Owner owner);

    public ResponseEntity<?> deleteById(long id);

    public ResponseEntity<?> deleteByEntity(Owner owner);

    public ResponseEntity<?> update(Owner owner);

    public ResponseEntity<?> getById(long id) throws ExecutionException, InterruptedException, TimeoutException;

    public ResponseEntity<?> getAll() throws ExecutionException, InterruptedException, TimeoutException;

    public ResponseEntity<?> deleteAll();
}
