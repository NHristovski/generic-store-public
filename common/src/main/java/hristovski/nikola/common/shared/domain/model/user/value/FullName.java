package hristovski.nikola.common.shared.domain.model.user.value;

import hristovski.nikola.generic_store.base.domain.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
@Builder
@ToString
public class FullName implements ValueObject {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    public String fullName(){
        return firstName + " " + lastName;
    }
}
