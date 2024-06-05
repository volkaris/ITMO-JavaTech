package Service.Cats;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
@Component
@Table(name = "cats")
public class Cat {

    @Id
    private Long id;

    @Column(name = "name")
    @Setter
    private String Name;

    @Column(name = "birthday")
    @Setter
    private LocalDate birthday;

    @Column(name = "race")
    @Setter
    private String race;

    @Column(name = "colour")
    @Setter
    private String colour;

    @Column(name = "owner_id")
    private Long ownerId;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "cats_friends",
            joinColumns = @JoinColumn(name = "cat_id")
    )
    @Column(name = "friend_id")
    private List<Long> friendsId;

    protected Cat() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return Objects.equals(id, cat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Cat(long id,
               String name,
               String race,
               String colour,
               LocalDate birthday,
               long ownerId) {
        this.id = id;
        Name = name;
        this.race = race;
        this.colour = colour;
        this.birthday = birthday;
        this.ownerId = ownerId;

    }
}
