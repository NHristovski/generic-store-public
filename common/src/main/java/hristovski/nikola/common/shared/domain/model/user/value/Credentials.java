package hristovski.nikola.common.shared.domain.model.user.value;

import hristovski.nikola.generic_store.base.domain.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
@Builder
public class Credentials implements ValueObject {
    @NotNull
    private String username;

    @NotNull
    private String password;

    public boolean matches(String username, String password) {
        Objects.requireNonNull(username, "username must not be null!");
        Objects.requireNonNull(password, "password must not be null!");

        return this.username.equals(username) && this.password.equals(password);
    }

    public Credentials(String username) {
        this.username = username;
        this.password = "*****";
    }


}
