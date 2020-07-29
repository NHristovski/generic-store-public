package hristovski.nikola.users.domain.factory;

import hristovski.nikola.generic_store.message.domain.rest.user.response.RegisterResponse;

public interface RegisterResponseFactory {
    RegisterResponse createSuccessfulRegisterResponse(String username);
    RegisterResponse createFailedRegisterResponse(String username);
}
