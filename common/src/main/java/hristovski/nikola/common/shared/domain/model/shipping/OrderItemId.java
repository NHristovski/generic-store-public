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
public class OrderItemId extends DomainObjectId {
    public OrderItemId() {
        super(DomainObjectId.randomId(OrderId.class).toString());
    }

    public OrderItemId(String id) {
        super(id);
    }
}
