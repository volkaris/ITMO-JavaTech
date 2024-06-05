package Ports.Owners;

import Service.Owners.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IOwnerRepository extends CrudRepository<Owner, Long> {
    Optional<Owner> getByName(String name);
    Optional<Owner> getById(Long id);
}
