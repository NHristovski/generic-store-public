package hristovski.nikola.users.application.converter;

import hristovski.nikola.common.shared.domain.model.user.ApplicationUser;
import hristovski.nikola.common.shared.domain.model.user.Role;
import hristovski.nikola.common.shared.domain.model.user.value.Credentials;
import hristovski.nikola.encryption.application.service.EncryptionService;
import hristovski.nikola.users.domain.persistance.entity.ApplicationUserEntity;
import hristovski.nikola.users.domain.persistance.entity.RoleEntity;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationUserEntityToApplicationUser implements Converter<ApplicationUserEntity, ApplicationUser> {

    private final EncryptionService encryptionService;

    @SneakyThrows
    @Override
    public ApplicationUser convert(ApplicationUserEntity applicationUserEntity) {
        ApplicationUser user = new ApplicationUser();

        user.setApplicationUserId(applicationUserEntity.getId());
        user.setVersion(applicationUserEntity.getVersion());
        user.setName(applicationUserEntity.getName());
        user.setEmail(applicationUserEntity.getEmail());
        user.setCustomerId(applicationUserEntity.getCustomerId());

        user.setCredentials(new Credentials(
                        applicationUserEntity.getCredentials().getUsername(),
                        encryptionService.encrypt(
                                applicationUserEntity.getCredentials().getPassword()
                        )
                )
        );

        Set<RoleEntity> roleEntities = applicationUserEntity.getRoles();
        Set<Role> roles = mapRoleEntitiesToRoles(roleEntities);
        user.setRoles(roles);

        return user;
    }

    private Set<Role> mapRoleEntitiesToRoles(Set<RoleEntity> roleEntities) {
        return roleEntities.stream()
                .map(role -> new Role(role.getVersion(), role.getId(), role.getName()))
                .collect(Collectors.toSet());
    }
}
