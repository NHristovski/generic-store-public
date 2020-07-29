package hristovski.nikola.common.shared.domain.model.shipping;

import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Embeddable;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
@Embeddable

public class ShippingId extends DomainObjectId {
    public ShippingId() {
        super(DomainObjectId.randomId(ShippingId.class).toString());
    }

    public ShippingId(String id) {
        super(id);
    }
}
