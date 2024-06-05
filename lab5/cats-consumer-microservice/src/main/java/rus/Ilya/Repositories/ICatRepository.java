package rus.Ilya.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rus.Ilya.Cats.Cat;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface ICatRepository extends JpaRepository<Cat,Long> {

    Optional<Cat> getCatById(Long id);

    @Modifying
    @Query(value = "INSERT INTO cats_friends (cat_id, friend_id) VALUES (:firstFriendId,:secondFriendId)", nativeQuery = true)
    @Transactional
    public void addFriend(long firstFriendId, long secondFriendId);

}
