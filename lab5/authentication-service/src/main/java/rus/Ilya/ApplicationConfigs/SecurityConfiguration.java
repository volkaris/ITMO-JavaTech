package rus.Ilya.ApplicationConfigs;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import rus.Ilya.JWT.Filters.JwtAuthentificationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final JwtAuthentificationFilter jwtAuthentificationFilter;

    private final AuthenticationProvider authenticationProvider;

    @Autowired
    public SecurityConfiguration(JwtAuthentificationFilter jwtAuthentificationFilter,
                                 AuthenticationProvider authenticationProvider) {
        this.jwtAuthentificationFilter = jwtAuthentificationFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth  .requestMatchers("/security/authorize").permitAll()
                                .requestMatchers(HttpMethod.POST,"/owners").hasAuthority("Admin")
                                .requestMatchers(HttpMethod.DELETE,"/cats/all").hasAuthority("Admin")
                                .requestMatchers(HttpMethod.GET,"/cats/all").hasAuthority("Admin")
                                .requestMatchers("/owners/**").hasAuthority("Admin")
                                .requestMatchers("/cats/**").hasAnyAuthority("Admin","User")
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(management ->
                        management
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthentificationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();

    }

}
