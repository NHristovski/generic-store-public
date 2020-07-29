package hristovski.nikola.common.shared.domain.model.all.value;

import hristovski.nikola.generic_store.base.domain.ValueObject;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Name implements ValueObject {
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
