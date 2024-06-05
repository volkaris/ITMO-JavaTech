package rus.Ilya.AuthServices;


import rus.Ilya.Entities.AuthenticationRequest;
import rus.Ilya.Entities.AuthenticationResponse;

public interface IAuthenticationService {
    public AuthenticationResponse authenticate(AuthenticationRequest request);

}
