package Ports.Cats;

import Service.Cats.Cat;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICatRepository extends CrudRepository<Cat,Long> {

    public Optional<Cat> getCatById(long Id);

    public List<Cat> getCatsByOwnerId(long id);

    @Modifying
    @Query(value = "INSERT INTO cats_friends (cat_id, friend_id) VALUES (:firstFriendId,:secondFriendId); INSERT INTO cats_friends (cat_id, friend_id) VALUES (:secondFriendId,:firstFriendId)"    , nativeQuery = true)
    @Transactional
    public void addFriend(long firstFriendId, long secondFriendId);

}
