package Service.Owners;

import Roles.Role;
import Service.Cats.Cat;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class OwnerDto {

    @Setter
    private Long id;
    @Setter
    private String name;
    @Setter
    private LocalDate birthday;

    @Setter
    private String password;

    @Getter
    private Role role;

    @Getter
    List<Cat> cat = new ArrayList<>();

    public OwnerDto(Long id,
                    String name,
                    LocalDate birthday,
                    String password,
                    Role role) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.password = password;
        this.role = role;

    }

    public OwnerDto(Long id, String name, LocalDate birthday, List<Cat> cat) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.cat = cat;
    }

    public OwnerDto(Owner newOwner) {
        this.id = newOwner.getId();
        this.name = newOwner.getName();
        this.birthday = newOwner.getBirthday();
        this.cat = newOwner.getCat();
        this.password = newOwner.getPassword();
        this.role = newOwner.getRole();


    }

}
