package hristovski.nikola.payment.domain.remote.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import hristovski.nikola.common.shared.domain.exception.RestRequestException;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUser;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.generic_store.message.domain.rest.user.response.GetApplicationUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final EurekaClient discoveryClient;
    private final RestTemplate restTemplate;
    private final static String USER_SERVICE = "user-service";

    @Override
    public ApplicationUser getUserById(ApplicationUserId applicationUserId) throws RestRequestException {
        InstanceInfo userServiceInfo = discoveryClient
                .getNextServerFromEureka(USER_SERVICE, false);


        String url = userServiceInfo.getHomePageUrl() + "private/" + applicationUserId.getId();

        log.info("Sending REST request to {}", url);

        GetApplicationUserResponse applicationUserResponse =
                restTemplate.getForObject(url, GetApplicationUserResponse.class);

        if (applicationUserResponse == null || applicationUserResponse.getUser() == null) {
            log.error("Failed to get user by username application user is null");
            throw new RestRequestException("User: " + applicationUserId.getId() + " not found");
        }

        return applicationUserResponse.getUser();
    }
}
