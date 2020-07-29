package hristovski.nikola.users.domain.persistance.repository;

import hristovski.nikola.common.shared.domain.model.user.RoleId;
import hristovski.nikola.common.shared.domain.model.user.RoleName;
import hristovski.nikola.users.domain.persistance.entity.RoleEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Primary
public interface RoleRepository extends JpaRepository<RoleEntity, RoleId> {
    Optional<RoleEntity> findByName(RoleName roleName);
}
