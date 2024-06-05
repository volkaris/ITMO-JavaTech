package rus.Ilya.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import rus.Ilya.AuthServices.IAuthenticationService;
import rus.Ilya.Entities.AuthenticationRequest;
import rus.Ilya.Entities.AuthenticationResponse;

@Controller
@RequestMapping(value = "/security")
public class AuthController implements IAuthController {

    private final IAuthenticationService securityService;

    public AuthController(IAuthenticationService securityService) {
        this.securityService = securityService;
    }

    @Override
    @PostMapping("/authorize")
    public ResponseEntity<AuthenticationResponse> authorize(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(securityService.authenticate(request));
    }
}
