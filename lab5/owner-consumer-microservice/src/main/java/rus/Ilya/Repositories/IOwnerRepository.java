package rus.Ilya.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rus.Ilya.Owners.Owner;

@Repository
public interface IOwnerRepository extends JpaRepository<Owner,Long> {
}
