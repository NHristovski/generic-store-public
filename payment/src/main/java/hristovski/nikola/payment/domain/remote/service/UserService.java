package hristovski.nikola.payment.domain.remote.service;

import hristovski.nikola.common.shared.domain.exception.RestRequestException;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUser;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;

public interface UserService {
    public ApplicationUser getUserById(ApplicationUserId applicationUserId)
        throws RestRequestException;
}
