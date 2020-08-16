package hristovski.nikola.generic_store.order.domain.persistance.entity;

import hristovski.nikola.common.shared.domain.model.all.value.Money;
import hristovski.nikola.common.shared.domain.model.order.Order;
import hristovski.nikola.common.shared.domain.model.shipping.OrderId;
import hristovski.nikola.common.shared.domain.model.shipping.value.Address;
import hristovski.nikola.common.shared.domain.model.shipping.value.ShippingStatus;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.generic_store.base.domain.AbstractEntity;
import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity extends AbstractEntity<OrderId> {
    @Version
    private Long version;

    private Instant createdOn;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ShippingStatus status;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "user_id", nullable = false))
    private ApplicationUserId userId;

    @Embedded
    private Money price;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<OrderItemEntity> orderItemEntities;

    @Embedded
    private Address shippingAddress;

    public OrderEntity(ApplicationUserId applicationUserId, Address address, Money price) {
        super(DomainObjectId.randomId(OrderId.class));
        this.version = 0L;
        this.createdOn = Instant.now();
        this.status = ShippingStatus.NOT_READY;
        this.userId = applicationUserId;
        this.shippingAddress = address;
        this.price = price;
        this.orderItemEntities = new HashSet<>();
    }

    public OrderEntity(ApplicationUserId applicationUserId, Address address, Money price,
                       Set<OrderItemEntity> orderItemEntities) {
        super(DomainObjectId.randomId(OrderId.class));
        this.version = 0L;
        this.createdOn = Instant.now();
        this.status = ShippingStatus.NOT_READY;
        this.userId = applicationUserId;
        this.shippingAddress = address;
        this.price = price;
        this.orderItemEntities = orderItemEntities;
    }

    public void orderReady() {
        this.status = ShippingStatus.READY;
    }

    public void pickUpOrder() {
        this.status = ShippingStatus.DELIVERING;
    }

    public void orderDelivered() {
        this.status = ShippingStatus.DELIVERED;
    }

    public Order toOrder() {
        return new Order(this.id(), this.version, this.createdOn,
                this.status, this.userId, this.price,
                this.orderItemEntities.stream()
                        .map(OrderItemEntity::toOrderItem)
                        .collect(Collectors.toSet())
                , this.shippingAddress);
    }
}
