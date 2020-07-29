package hristovski.nikola.generic_store.auth.application.configuration;


import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUser;
import hristovski.nikola.encryption.application.service.EncryptionService;
import hristovski.nikola.generic_store.message.domain.rest.user.response.GetApplicationUserResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Gets user details for given username
 */
@Service("customUserDetailsService")
@Slf4j
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String SERVICE = "users-service";
    private final EurekaClient discoveryClient;
    private final RestTemplate restTemplate;
    private final EncryptionService encryptionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            InstanceInfo userServiceInfo = discoveryClient.getNextServerFromEureka(SERVICE, false);
            log.info("The server url is: {}", userServiceInfo.getHomePageUrl());

            String url = userServiceInfo.getHomePageUrl() + username;

            GetApplicationUserResponse applicationUserResponse = restTemplate.getForObject(url, GetApplicationUserResponse.class);

            if (applicationUserResponse == null || applicationUserResponse.getUser() == null) {
                log.error("Failed to get user by username application user is null");
                throw new UsernameNotFoundException("Username: " + username + " not found");
            }

            ApplicationUser user = applicationUserResponse.getUser();

            return new User(
                    user.getCredentials().getUsername(),
                    encryptionService.decrypt(
                            user.getCredentials().getPassword()
                    ),
                    AuthorityUtils.commaSeparatedStringToAuthorityList(
                            user.getRolesAsString()
                    )
            );

        } catch (Exception ex) {
            log.error("Failed to get user by username", ex);
            throw new UsernameNotFoundException("Username: " + username + " not found");
        }
    }
}
