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
public class ShoppingCartItemId extends DomainObjectId {
    public ShoppingCartItemId(String id) {
        super(id);
    }

    public ShoppingCartItemId() {
        super(DomainObjectId.randomId(ShoppingCartItemId.class).toString());
    }

    //TODO MAYBE UNCOMMENT THIS
//    @Override
//    public boolean equals(Object o) {
//        return super.equals(o);
//    }
//
//    @Override
//    public int hashCode() {
//        return super.hashCode();
//    }
}
