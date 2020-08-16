package hristovski.nikola.users.domain.service;

import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.generic_store.message.domain.rest.user.request.RegisterRequest;
import hristovski.nikola.generic_store.message.domain.rest.user.response.GetApplicationUserResponse;
import hristovski.nikola.generic_store.message.domain.rest.user.response.RegisterResponse;

public interface UserService {

    RegisterResponse registerUser(RegisterRequest registerRequest);

    GetApplicationUserResponse getUser(String username);

    GetApplicationUserResponse getUser(ApplicationUserId applicationUserId);

    void updateCustomerId(ApplicationUserId applicationUserId, String customerId);
}
