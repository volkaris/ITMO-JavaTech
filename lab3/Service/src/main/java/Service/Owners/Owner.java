package Service.Owners;

import Service.Cats.Cat;
import Service.Cats.CatDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Component
@Table(name = "owners")

public class Owner {

    @Getter
    @Id
    Long id;

    @Column(name = "name")
    @Setter
    @Getter
    String name;

    @Column(name = "birthday")
    @Getter
    @Temporal(TemporalType.DATE)
    LocalDate birthday;

    @OneToMany(mappedBy = "ownerId",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    List<Cat> cat = new ArrayList<>();

    protected Owner() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return Objects.equals(id, owner.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Owner(Owner newOwner) {
        this.id = newOwner.id;
        this.name = newOwner.name;
        this.birthday = newOwner.birthday;
        this.cat = newOwner.cat;
    }

    public Owner(long id, String name, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }
}
