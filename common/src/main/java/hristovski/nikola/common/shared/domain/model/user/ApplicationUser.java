package hristovski.nikola.common.shared.domain.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import hristovski.nikola.common.shared.domain.model.user.value.Credentials;
import hristovski.nikola.common.shared.domain.model.user.value.EmailAddress;
import hristovski.nikola.common.shared.domain.model.user.value.FullName;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Transient;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
public class ApplicationUser {

    private Long version;
    private ApplicationUserId applicationUserId;
    private FullName name;
    private EmailAddress email;
    private Credentials credentials;
    private Set<Role> roles;

    public ApplicationUser() {
        applicationUserId = DomainObjectId.randomId(ApplicationUserId.class);
        roles = new HashSet<>();
    }

    public ApplicationUser(FullName name, EmailAddress email, Credentials credentials) {
        applicationUserId = DomainObjectId.randomId(ApplicationUserId.class);
        this.name = name;
        this.email = email;
        this.credentials = credentials;
        roles = new HashSet<>();
    }

    public ApplicationUser(FullName name, EmailAddress email, Credentials credentials, Set<Role> roles) {
        applicationUserId = DomainObjectId.randomId(ApplicationUserId.class);
        this.name = name;
        this.email = email;
        this.credentials = credentials;
        this.roles = roles;
    }

    public ApplicationUser(ApplicationUserId id, FullName name, EmailAddress email, Credentials credentials, Set<Role> roles) {
        this.applicationUserId = id;
        this.name = name;
        this.email = email;
        this.credentials = credentials;
        this.roles = roles;
    }

    @JsonIgnore
    @Transient
    public String getRolesAsString() {
        return this.getRoles().stream()
                .map(Role::getName)
                .map(Enum::name)
                .collect(Collectors.joining(","));
    }
}
