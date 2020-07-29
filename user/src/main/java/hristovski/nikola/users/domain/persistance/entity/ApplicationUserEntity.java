package hristovski.nikola.users.domain.persistance.entity;

import hristovski.nikola.generic_store.base.domain.AbstractEntity;
import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.common.shared.domain.model.user.value.Credentials;
import hristovski.nikola.common.shared.domain.model.user.value.EmailAddress;
import hristovski.nikola.common.shared.domain.model.user.value.FullName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "users")
public class ApplicationUserEntity extends AbstractEntity<ApplicationUserId> {

    @Version
    private Long version;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "first_name", nullable = false)),
            @AttributeOverride(name = "lastName", column = @Column(name = "last_name", nullable = false)),
    })
    private FullName name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "email", column = @Column(name = "email", nullable = false, unique = true))
    })
    private EmailAddress email;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "username", column = @Column(name = "username", nullable = false, unique = true)),
            @AttributeOverride(name = "password", column = @Column(name = "password", nullable = false)),
    })
    private Credentials credentials;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<RoleEntity> roles;

    public ApplicationUserEntity() {
        super(DomainObjectId.randomId(ApplicationUserId.class));
        //        roles = new ArrayList<>();
    }

    public ApplicationUserEntity(FullName name, EmailAddress email, Credentials credentials) {
        this();
        this.name = name;
        this.email = email;
        this.credentials = credentials;
//        roles = new ArrayList<>();
    }

    public ApplicationUserEntity(FullName name, EmailAddress email, Credentials credentials, Set<RoleEntity> roles) {
        this(name, email, credentials);
        this.roles = roles;
    }

    public ApplicationUserEntity(ApplicationUserId id, FullName name, EmailAddress email, Credentials credentials, Set<RoleEntity> roles) {
        super(id);
        this.name = name;
        this.email = email;
        this.credentials = credentials;
        this.roles = roles;
    }

    public void setEmail(EmailAddress email) {
        if (!email.valid()) {
            throw new RuntimeException("Not a valid email address!");
        }

        this.email = email;
    }

    public String getRolesAsString() {
        return this.getRoles().stream()
                .map(RoleEntity::getName)
                .map(Enum::name)
                .peek(System.out::println)
                .collect(Collectors.joining(","));
    }

}
