package Service.Owners;

import Service.Cats.Cat;
import Service.Cats.CatDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OwnerDto {

    @Getter
    Long id;

    @Getter
    @Setter
    String name;


    @Getter
    @Setter
    LocalDate birthday;

    @Getter
    List<Cat> cat = new ArrayList<>();
    public OwnerDto(Long id, String name, LocalDate birthday, List<Cat> cat) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.cat = cat;
    }

    public OwnerDto(Owner newOwner) {
        this.id = newOwner.id;
        this.name = newOwner.name;
        this.birthday = newOwner.birthday;
        this.cat = newOwner.cat;
    }

    public OwnerDto(long id, String name, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }
}
