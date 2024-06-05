package rus.Ilya.AuthServices;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rus.Ilya.Entities.AuthenticationRequest;
import rus.Ilya.Entities.AuthenticationResponse;
import rus.Ilya.JWT.Services.IJwtService;
import rus.Ilya.Owners.OwnerDto;
import rus.Ilya.Repos.IOwnerRepository;


@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

    private final IOwnerRepository repo;
    private final IJwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getId(),
                        request.getPassword()
                )
        );

        var optionalOwner = repo.getById(Long.valueOf(request.getId()));

        if (optionalOwner.isEmpty()){
            throw new UsernameNotFoundException("user not found");
        }

        var owner=optionalOwner.get();

        var jwt = jwtService.generateJwt(owner);
        return new AuthenticationResponse(new OwnerDto(owner),jwt);
    }
}
