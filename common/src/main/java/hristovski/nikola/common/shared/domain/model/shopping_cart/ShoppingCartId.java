package hristovski.nikola.common.shared.domain.model.shopping_cart;

import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@EqualsAndHashCode(callSuper = true)
@Embeddable
public class ShoppingCartId extends DomainObjectId {
    public ShoppingCartId() {
        super(DomainObjectId.randomId(ShoppingCartId.class).toString());
    }

    public ShoppingCartId(String id) {
        super(id);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
