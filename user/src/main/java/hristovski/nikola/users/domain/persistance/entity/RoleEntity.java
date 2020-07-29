package hristovski.nikola.users.domain.persistance.entity;

import hristovski.nikola.generic_store.base.domain.AbstractEntity;
import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import hristovski.nikola.common.shared.domain.model.user.RoleId;
import hristovski.nikola.common.shared.domain.model.user.RoleName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class RoleEntity extends AbstractEntity<RoleId> {

    @Version
    private Long version = 0L;

    @Column(name = "role_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleName name;


    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "roles")
    private Set<ApplicationUserEntity> users;

    public RoleEntity(String roleName) {
        super(DomainObjectId.randomId(RoleId.class));
        this.name = RoleName.valueOf(roleName);
        version = 0L;
//        users = new HashSet<>();
    }

    public RoleEntity(RoleName roleName) {
        super(DomainObjectId.randomId(RoleId.class));
        this.name = roleName;
        version = 0L;
//        users = new HashSet<>();
    }


}
