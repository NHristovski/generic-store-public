package hristovski.nikola.common.shared.domain.model.all.value;

import hristovski.nikola.generic_store.base.domain.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Name implements ValueObject {
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
