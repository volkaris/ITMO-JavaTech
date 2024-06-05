package rus.Ilya.JWT.Services;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface IJwtService {
    public String extractUsername(String jwt);
    public String generateJwt(UserDetails userDetails);
    public String generateJwt(Map<String, Object> extraClaims, UserDetails userDetails);

    public boolean isJwtValid(String jwt, UserDetails userDetails);
}
