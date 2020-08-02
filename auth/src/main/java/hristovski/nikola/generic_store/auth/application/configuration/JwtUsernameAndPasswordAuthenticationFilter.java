package hristovski.nikola.generic_store.auth.application.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import hristovski.nikola.common.shared.domain.constants.Headers;
import hristovski.nikola.generic_store.message.domain.rest.user.response.GetApplicationUserResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.client.RestTemplate;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Sets the authentication path to /auth
 * Uses the auth manager to try and authenticate
 * Generates jwt token after successful authentication
 */
@Slf4j
public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authManager;
    private final EurekaClient discoveryClient;
    private final RestTemplate restTemplate;

    private final JwtConfig jwtConfig;
    private final ServicesConfig servicesConfig;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authManager, EurekaClient discoveryClient,
                                                      RestTemplate restTemplate, JwtConfig jwtConfig,
                                                      ServicesConfig servicesConfig) {
        this.authManager = authManager;
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;

        this.jwtConfig = jwtConfig;
        this.servicesConfig = servicesConfig;
        // By default, UsernamePasswordAuthenticationFilter listens to "/login" path.
        // This will override it to listen to the configured url ( default "/auth" ).
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(jwtConfig.getUri(), "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {

            log.info("Attempting authentication");
            // Get credentials from request
            UserCredentials creds = new ObjectMapper().readValue(request.getInputStream(), UserCredentials.class);

            // 2. Create auth object (contains credentials) which will be used by the auth manager
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    creds.getUsername(), creds.getPassword(), Collections.emptyList());

            // 3. Authentication manager authenticate the user, and use UserDetialsServiceImpl::loadUserByUsername() method to load the user.
            return authManager.authenticate(authToken);

        } catch (IOException e) {
            log.error("AuthManager.authenticate failed.", e);
            throw new RuntimeException(e);
        }
    }

    // Upon successful authentication, generate a token.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        long now = System.currentTimeMillis();

        List<String> authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());


        GetApplicationUserResponse applicationUserResponse = getApplicationUserResponse(auth);
        String userId = applicationUserResponse.getUser().getApplicationUserId().getId();

        // Create the token
        String token = Jwts.builder()
                .setSubject(auth.getName())
                .claim("authorities", authorities)
                .claim(Headers.USER_ID, userId)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfig.getExpiration() * 1000))  // in milliseconds
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
                .compact();

        // Add token to header
        response.addHeader(jwtConfig.getHeader(), jwtConfig.getPrefix() + token);

        response.addHeader("roles", authorities.toString());
        response.addHeader("id", userId);

        log.info("Auth for user {} successful. Token generation successful", userId);
    }

    private GetApplicationUserResponse getApplicationUserResponse(Authentication auth) {
        InstanceInfo userServiceInfo = discoveryClient
                .getNextServerFromEureka(servicesConfig.getUserService(), false);

        String url = userServiceInfo.getHomePageUrl() + auth.getName();

        GetApplicationUserResponse applicationUserResponse =
                restTemplate.getForObject(url, GetApplicationUserResponse.class);

        assert applicationUserResponse != null;

        return applicationUserResponse;
    }

    @Getter
    @Setter
    @ToString
    // A (temporary) class just to represent the user credentials
    private static class UserCredentials {
        private String username;
        private String password;
    }
}
