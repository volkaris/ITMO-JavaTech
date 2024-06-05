package rus.Ilya.Controllers;

import org.springframework.http.ResponseEntity;
import rus.Ilya.Entities.AuthenticationRequest;
import rus.Ilya.Entities.AuthenticationResponse;

public interface IAuthController {

    public ResponseEntity<AuthenticationResponse> authorize(AuthenticationRequest request);
}
