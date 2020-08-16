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
public class OrderId extends DomainObjectId {
    public OrderId() {
        super(DomainObjectId.randomId(OrderId.class).toString());
    }

    public OrderId(String id) {
        super(id);
    }
}
