package hristovski.nikola.users.domain.persistance.repository;

import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.users.domain.persistance.entity.ApplicationUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUserEntity, ApplicationUserId> {
    Optional<ApplicationUserEntity> findByCredentialsUsername(String username);
}
