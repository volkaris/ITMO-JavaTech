package Service.Cats;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Component
@Table(name = "cats")
public class Cat {

    @Id
    @Getter
    private Long id;

    @Column(name = "name")
    @Getter
    @Setter
    private String Name;

    @Column(name = "birthday")
    @Getter
    @Setter
    private LocalDate birthday;

    @Column(name = "race")
    @Getter
    @Setter
    private String race;

    @Column(name = "colour")
    @Getter
    @Setter
    private String colour;

    @Column(name = "owner_id")
    @Getter
    private Long ownerId;

    //todo https://habr.com/ru/companies/otus/articles/529692/

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "cats_friends",
            joinColumns = @JoinColumn(name = "cat_id")
    )
    @Column(name = "friend_id")
    @Getter
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
