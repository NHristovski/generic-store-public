package hristovski.nikola.users.domain.service;

import hristovski.nikola.common.shared.domain.model.user.ApplicationUser;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.common.shared.domain.model.user.RoleName;
import hristovski.nikola.generic_store.message.domain.rest.user.request.RegisterRequest;
import hristovski.nikola.generic_store.message.domain.rest.user.response.GetApplicationUserResponse;
import hristovski.nikola.generic_store.message.domain.rest.user.response.RegisterResponse;
import hristovski.nikola.users.domain.factory.RegisterResponseFactory;
import hristovski.nikola.users.domain.persistance.entity.ApplicationUserEntity;
import hristovski.nikola.users.domain.persistance.repository.ApplicationUserRepository;
import hristovski.nikola.users.domain.persistance.repository.RoleRepository;
import hristovski.nikola.users.exception.FailedToCreateUserException;
import hristovski.nikola.users.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ApplicationUserRepository applicationUserRepository;
    private final RoleRepository roleRepository;
    private final ConversionService conversionService;
    private final RegisterResponseFactory registerResponseFactory;

    @Override
    public RegisterResponse registerUser(RegisterRequest registerRequest) {
        try {
            ApplicationUserEntity user = conversionService.convert(registerRequest, ApplicationUserEntity.class);

            Objects.requireNonNull(user, "User must not be null!");
            log.info("The user is not null");

//            applicationUserRepository.save(user);

            user.setRoles(Collections.singleton(roleRepository.findByName(RoleName.ROLE_USER).get()));

            log.info("Set roles finished");

            applicationUserRepository.save(user);

            log.info("Register successful");

            return registerResponseFactory
                    .createSuccessfulRegisterResponse(
                            registerRequest.getUsername()
                    );

        } catch (DataIntegrityViolationException ex) {
            log.error("Failed to create user because of data integrity violation", ex);

            throw new FailedToCreateUserException("Failed to create user because the data provided violates the data integrity!");
        } catch (Exception ex) {
            log.error("Failed to create user", ex);

            throw new FailedToCreateUserException("Failed to create user. Message: " + ex.getMessage());
        }
    }

    @Override
    public GetApplicationUserResponse getUser(String username) {
        log.info("Getting user for username: {}", username);

        ApplicationUserEntity applicationUserEntity = applicationUserRepository.findByCredentialsUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        log.info("Entity {} ", applicationUserEntity);

        try {
            ApplicationUser user = conversionService.convert(applicationUserEntity, ApplicationUser.class);

            log.info("After conversion  user {} ", user);

            Objects.requireNonNull(user, "User must not be null!");

            return GetApplicationUserResponse.builder().user(user).build();
        } catch (Exception ex) {
            throw new RuntimeException("Failed to get user with username " + username, ex);
        }
    }

    @Override
    public GetApplicationUserResponse getUser(ApplicationUserId applicationUserId) {
        ApplicationUserEntity applicationUserEntity = applicationUserRepository.findById(applicationUserId)
                .orElseThrow(() -> new UserNotFoundException(applicationUserId.getId()));

        try {
            ApplicationUser user = conversionService.convert(applicationUserEntity, ApplicationUser.class);

            Objects.requireNonNull(user, "User must not be null!");

            return GetApplicationUserResponse.builder().user(user).build();
        } catch (Exception ex) {
            throw new RuntimeException("Failed to get user with id " + applicationUserId.getId(), ex);
        }
    }

    @Override
    public void updateCustomerId(ApplicationUserId applicationUserId, String customerId) {
        ApplicationUserEntity applicationUserEntity = applicationUserRepository.findById(applicationUserId)
                .orElseThrow(() -> new UserNotFoundException(applicationUserId.getId()));

        applicationUserEntity.updateCustomerId(customerId);

        applicationUserRepository.saveAndFlush(applicationUserEntity);
    }
}
