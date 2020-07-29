package hristovski.nikola.common.shared.domain.model.product;

import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Embeddable;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
@Embeddable
public class ProductId extends DomainObjectId {
    public ProductId() {
        super(DomainObjectId.randomId(ProductId.class).toString());
    }

    public ProductId(String id) {
        super(id);
    }
}
