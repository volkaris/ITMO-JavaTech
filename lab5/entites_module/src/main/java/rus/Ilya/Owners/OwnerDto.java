package rus.Ilya.Owners;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import rus.Ilya.Cats.Cat;
import rus.Ilya.Roles.Role;

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


    @Override
    public String toString() {
        return "OwnerDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", cat=" + cat +
                '}';
    }


    @JsonCreator
    public OwnerDto(@JsonProperty("id") Long id,
                    @JsonProperty("name") String name,
                    @JsonProperty("birthday") LocalDate birthday,
                    @JsonProperty("password") String password,
                    @JsonProperty("role") Role role) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.password=password;
        this.role=role;
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
