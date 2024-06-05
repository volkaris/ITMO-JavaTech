package rus.Ilya.Entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import rus.Ilya.Owners.OwnerDto;


@AllArgsConstructor
@Getter
public class AuthenticationResponse {

    @JsonProperty("owner")
    private OwnerDto owner;

    @JsonProperty("access_token")
    private String accessToken;
}
