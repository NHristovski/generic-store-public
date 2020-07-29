package hristovski.nikola.common.shared.domain.model.inventory;

import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class ProductStockId extends DomainObjectId {
    public ProductStockId() {
        super(DomainObjectId.randomId(ProductStockId.class).toString());
    }

    public ProductStockId(String id) {
        super(id);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

