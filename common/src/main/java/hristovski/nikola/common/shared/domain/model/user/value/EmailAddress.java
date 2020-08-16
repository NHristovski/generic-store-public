package hristovski.nikola.common.shared.domain.model.user.value;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hristovski.nikola.generic_store.base.domain.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.regex.Pattern;

@AllArgsConstructor
@Data
@Embeddable
@Builder
@ToString
public class EmailAddress implements ValueObject {
    @Transient
    @JsonIgnore
    private static final String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    @JsonIgnore
    @Transient
    private final Pattern pattern;

    @Getter
    @NotNull
    private String email;

    public EmailAddress() {
        pattern = Pattern.compile(regex);
    }

    public EmailAddress(String email) {
        Objects.requireNonNull(email, "Email must not be null!");

        this.email = email;

        pattern = Pattern.compile(regex);
    }

    public boolean valid() {
        return pattern.matcher(email).matches();
    }

    public void setEmail(@NonNull String email) {
        Objects.requireNonNull(email, "Email must not be null!");

        this.email = email;
    }
}
