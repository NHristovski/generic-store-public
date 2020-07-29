package hristovski.nikola.common.shared.domain.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Version;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Version
    private Long version;

    @EmbeddedId
    private RoleId id;

    @Column(name = "role_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleName name;
}
