package Service.Cats;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CatDto {

    @Getter
    private Long id;

    @Getter
    @Setter
    private String Name;

    @Getter
    @Setter
    private LocalDate birthday;

    @Getter
    @Setter
    private String race;

    @Getter
    @Setter
    private String colour;

    @Getter
    @Setter
    private Long ownerId;

    @Getter
    private List<Long> friendsId;

    protected CatDto() {
    }

    public CatDto(long id,
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

    public CatDto(Cat cat) {
        this.id = cat.getId();
        this.Name = cat.getName();
        this.race = cat.getRace();
        this.colour = cat.getColour();
        this.birthday = cat.getBirthday();
        this.ownerId = cat.getOwnerId();
        this.friendsId=cat.getFriendsId();
    }
}
