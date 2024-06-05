package rus.Ilya.Owners;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import rus.Ilya.Cats.Cat;


import jakarta.persistence.*;
import rus.Ilya.Roles.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Component
@Table(name = "owners")
@Proxy(lazy = false)
public class Owner implements UserDetails {

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


    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Getter
    private Role role;

    @OneToMany(mappedBy = "ownerId",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    @Getter
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


    public Owner(long id, String name, String password, Role role, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.birthday = birthday;
    }


    public Owner(OwnerDto newOwner) {
        this.id = newOwner.getId();
        this.name = newOwner.getName();
        this.birthday = newOwner.getBirthday();
        this.cat = newOwner.getCat();
        this.password= newOwner.getPassword();
        this.role=newOwner.getRole();
    }

   /* public Owner(OwnerDto newOwner) {
        this.id = newOwner.getId();
        this.name = newOwner.getName();
        this.birthday = newOwner.getBirthday();
        this.cat = newOwner.getCat();
    }

    public Owner(long id, String name, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }*/
   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
       return List.of(new SimpleGrantedAuthority(role.name()));
   }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return id.toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
