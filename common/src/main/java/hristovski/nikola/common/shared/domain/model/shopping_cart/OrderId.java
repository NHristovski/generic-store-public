package hristovski.nikola.common.shared.domain.model.shopping_cart;

import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Embeddable;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
@Embeddable
public class OrderId extends DomainObjectId {

    public OrderId(String id) {
        super(id);
    }

    public OrderId() {
        super(DomainObjectId.randomId(ShoppingCartId.class).toString());

    }
}
