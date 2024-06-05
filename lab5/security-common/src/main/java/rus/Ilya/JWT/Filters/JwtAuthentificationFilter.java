package rus.Ilya.JWT.Filters;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import rus.Ilya.JWT.Services.IJwtService;

import java.io.IOException;

@Component
public class JwtAuthentificationFilter extends OncePerRequestFilter {

    @Autowired
    public JwtAuthentificationFilter(UserDetailsService ownerService,
                                     IJwtService jwtService) {
        this.ownerService = ownerService;
        this.jwtService = jwtService;
    }

    private final UserDetailsService ownerService;

    private final IJwtService jwtService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.substring(7);
        String userName = jwtService.extractUsername(jwt);
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var  owner = ownerService.loadUserByUsername(userName);

            if (jwtService.isJwtValid(jwt,owner)){
                var authToken=new UsernamePasswordAuthenticationToken(
                        owner,null,owner.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(request)
                );
            SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);

    }
}
