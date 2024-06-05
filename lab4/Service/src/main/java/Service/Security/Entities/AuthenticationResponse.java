package Service.Security.Entities;

import Service.Owners.OwnerDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@AllArgsConstructor
@Getter
public class AuthenticationResponse {

    @JsonProperty("owner")
    private OwnerDto owner;

    @JsonProperty("access_token")
    private String accessToken;  //jwt token that will be send back to user
}
