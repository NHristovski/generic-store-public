package hristovski.nikola.shopping_cart.domain.model;

import hristovski.nikola.common.shared.domain.model.shopping_cart.OrderId;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.generic_store.base.domain.AbstractEntity;
import lombok.Data;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "order")
@Data
public class OrderEntity extends AbstractEntity<OrderId> {

    Instant expiresOn;

    boolean finished;

    @Version
    private Long version;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "user_id", nullable = false))
    private ApplicationUserId userId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ShoppingCartItemEntity> shoppingCartItemEntities;

    // TODO SCHEDULED JOB TO CHECK FOR ORDERS that are not finished and expired
    // then send event to the inventory service
}
