package Service.Security.AuthServices;

import Service.Security.Entities.AuthenticationRequest;
import Service.Security.Entities.AuthenticationResponse;

public interface IAuthenticationService {
    public AuthenticationResponse authenticate(AuthenticationRequest request);

}
