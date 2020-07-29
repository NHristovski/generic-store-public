package hristovski.nikola.common.shared.domain.model.category;

import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Embeddable;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
@Embeddable
public class CategoryId extends DomainObjectId {
    public CategoryId() {
        super(DomainObjectId.randomId(CategoryId.class).toString());
    }

    public CategoryId(String id) {
        super(id);
    }
}
