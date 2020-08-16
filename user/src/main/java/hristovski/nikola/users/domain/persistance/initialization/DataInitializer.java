package hristovski.nikola.users.domain.persistance.initialization;

import hristovski.nikola.common.shared.domain.model.user.RoleName;
import hristovski.nikola.generic_store.message.domain.rest.user.request.RegisterRequest;
import hristovski.nikola.users.domain.persistance.entity.ApplicationUserEntity;
import hristovski.nikola.users.domain.persistance.entity.RoleEntity;
import hristovski.nikola.users.domain.persistance.repository.ApplicationUserRepository;
import hristovski.nikola.users.domain.persistance.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {

    private final ApplicationUserRepository applicationUserRepository;
    private final RoleRepository roleRepository;
    private final ConversionService conversionService;

    @Value("${admin.password}")
    private String password;

    @PostConstruct
    public void init() {
        log.info("DataInitializer called");
        insertRolesAndAdminUser();
    }

    public void insertRolesAndAdminUser() {
        insertRoles();
        insertAdminUser();
    }

    private void insertAdminUser() {
        Optional<ApplicationUserEntity> admin = applicationUserRepository.findByCredentialsUsername("admin");

        if (admin.isEmpty()) {

            RegisterRequest registerRequest
                    = new RegisterRequest(
                    "admin",
                    "admin",
                    "admin@gmail.com",
                    password,
                    password
            );
            try {
                ApplicationUserEntity adminUser = conversionService.convert(registerRequest, ApplicationUserEntity.class);
                applicationUserRepository.saveAndFlush(adminUser);

                log.info("Added admin user");

                assignAdminPermissions(adminUser);
            } catch (Exception ex) {
                log.error("Failed to add the admin user!", ex);
            }
        }
    }

    private void assignAdminPermissions(ApplicationUserEntity adminUser) {
        RoleEntity userRole = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(
                () -> new RuntimeException("There is not USER ROLE in the database!")
        );

        RoleEntity adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN).orElseThrow(
                () -> new RuntimeException("There is not ADMIN ROLE in the database!")
        );

        adminUser.setRoles(Set.of(userRole, adminRole));

        applicationUserRepository.saveAndFlush(adminUser);
    }

    private void insertRoles() {
        Optional<RoleEntity> roleUser = roleRepository.findByName(RoleName.ROLE_USER);

        if (roleUser.isEmpty()) {
            RoleEntity user = new RoleEntity(RoleName.ROLE_USER);
            roleRepository.saveAndFlush(user);
            log.info("Added User Role");
        }

        Optional<RoleEntity> roleAdmin = roleRepository.findByName(RoleName.ROLE_ADMIN);

        if (roleAdmin.isEmpty()) {
            RoleEntity admin = new RoleEntity(RoleName.ROLE_ADMIN);
            roleRepository.saveAndFlush(admin);
            log.info("Added Admin Role");
        }
    }
}
