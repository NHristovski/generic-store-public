package hristovski.nikola.users.application.converter;

import hristovski.nikola.common.shared.domain.model.user.value.Credentials;
import hristovski.nikola.common.shared.domain.model.user.value.EmailAddress;
import hristovski.nikola.common.shared.domain.model.user.value.FullName;
import hristovski.nikola.generic_store.message.domain.rest.user.request.RegisterRequest;
import hristovski.nikola.users.domain.persistance.entity.ApplicationUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterRequestToApplicationUserConverter implements Converter<RegisterRequest, ApplicationUserEntity> {

    private final BCryptPasswordEncoder encoder;

    @Override
    public ApplicationUserEntity convert(RegisterRequest registerRequest) {

        ApplicationUserEntity user = new ApplicationUserEntity();

        user.setEmail(new EmailAddress(registerRequest.getEmail()));

        user.setCredentials(new Credentials(
                        registerRequest.getUsername(),
                        encoder.encode(registerRequest.getPassword())
                )
        );
        //TODO change this to first name last name
        user.setName(new FullName(registerRequest.getName(), registerRequest.getName()));

        return user;
    }
}
