package Service.Cats;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
public class CatDto {

    private Long id;

    @Setter
    private String Name;

    @Setter
    private LocalDate birthday;

    @Setter
    private String race;

    @Setter
    private String colour;

    @Setter
    private Long ownerId;

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
