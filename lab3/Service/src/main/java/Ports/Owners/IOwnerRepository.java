package Ports.Owners;

import Service.Owners.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOwnerRepository extends CrudRepository<Owner,Long> { }
