package hristovski.nikola.common.shared.domain.model.product;

import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@EqualsAndHashCode(callSuper = true)
@Embeddable
public class ProductId extends DomainObjectId {
    public ProductId() {
        super(DomainObjectId.randomId(ProductId.class).toString());
    }

    public ProductId(String id) {
        super(id);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
