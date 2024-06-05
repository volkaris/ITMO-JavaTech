package Service.Owners;

import Ports.Owners.IOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerService implements IOwnerService {

    IOwnerRepository repository;

    @Autowired
    public OwnerService(IOwnerRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<OwnerDto> save(Owner owner) {

        if (repository.existsById(owner.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        var result = repository.save(owner);

        return ResponseEntity.ok(new OwnerDto(result));

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
