package hristovski.nikola.users.domain.factory;

import hristovski.nikola.generic_store.message.domain.rest.user.response.RegisterResponse;
import org.springframework.stereotype.Service;

@Service
public class RegisterResponseFactoryImpl implements RegisterResponseFactory {
    @Override
    public RegisterResponse createSuccessfulRegisterResponse(String username) {
        return RegisterResponse.builder()
                .message("User " + username + " registered successfully")
                .build();
    }

    @Override
    public RegisterResponse createFailedRegisterResponse(String username) {
        return RegisterResponse.builder()
                .message("Registration for user " + username + " failed")
                .build();
    }
}
