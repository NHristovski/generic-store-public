package hristovski.nikola.generic_store.shipping.domain.persistance.entity;

import hristovski.nikola.common.shared.domain.model.shipping.ShippingId;
import hristovski.nikola.common.shared.domain.model.shipping.value.Address;
import hristovski.nikola.common.shared.domain.model.shipping.value.ShippingStatus;
import hristovski.nikola.common.shared.domain.model.shopping_cart.OrderId;
import hristovski.nikola.generic_store.base.domain.AbstractEntity;
import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Version;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shipping")
public class ShippingEntity extends AbstractEntity<ShippingId> {
    @Version
    private Long version;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "order_id", nullable = false))
    private OrderId orderId;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ShippingStatus status;

    @Embedded
    private Address shippingAddress;

    public ShippingEntity(OrderId orderId, Address address) {
        super(DomainObjectId.randomId(ShippingId.class));
        this.version = 0L;
        this.orderId = orderId;
        this.status = ShippingStatus.IN_INVENTORY;
        this.shippingAddress = address;
    }

    public void pickUpOrder() {
        this.status = ShippingStatus.DELIVERING;
    }

    public void orderDelivered() {
        this.status = ShippingStatus.DELIVERED;
    }
}
