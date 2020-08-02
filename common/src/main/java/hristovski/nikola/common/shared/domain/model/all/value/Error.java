package hristovski.nikola.common.shared.domain.model.all.value;

import hristovski.nikola.generic_store.base.domain.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Error implements ValueObject{
    private String errorMessage;
    private boolean hasError;
}
