package rus.Ilya.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationRequest {

    private String id;
    private String password;
}