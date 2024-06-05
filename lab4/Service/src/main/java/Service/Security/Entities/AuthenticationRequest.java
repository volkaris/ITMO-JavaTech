package Service.Security.Entities;

import lombok.*;

@Getter
@AllArgsConstructor
public class AuthenticationRequest {

    private String id;
    private String password;
}