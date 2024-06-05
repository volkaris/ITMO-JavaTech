package Ports.Cats;

import Service.Cats.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ICatRepository extends JpaRepository<Cat,Long> {
    public List<Cat> getAllByOwnerId(long id);

    //todo https://www.baeldung.com/jpql-hql-criteria-query
    @Modifying
    @Query(value = "insert into cats_friends (cat_id, friend_id) VALUES (:firstFriendId,:secondFriendId); insert into cats_friends (cat_id, friend_id) VALUES (:secondFriendId,:firstFriendId)"    , nativeQuery = true)
    @Transactional
    public void addFriend(long firstFriendId, long secondFriendId);

}
