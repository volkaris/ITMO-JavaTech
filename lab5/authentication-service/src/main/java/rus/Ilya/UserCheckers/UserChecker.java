package rus.Ilya.UserCheckers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import rus.Ilya.Roles.Role;

import java.util.List;

public class UserChecker {

    public Long getCurrentUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return Long.valueOf(authentication.getName());
    }

    public List<GrantedAuthority> getCurrentUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return (List<GrantedAuthority>) authentication.getAuthorities();
    }

    public boolean currentUserIsAdmin() {

        var authorities = getCurrentUserRole();
        for (var i : authorities) {
            if (i.getAuthority().equals(Role.Admin.name())) {
                return true;
            }
        }
        return false;
    }

    public boolean currentUserIsUser() {

        var authorities = getCurrentUserRole();
        for (var i : authorities) {
            if (i.getAuthority().equals(Role.User.name())) {
                return true;
            }
        }
        return false;
    }
}
