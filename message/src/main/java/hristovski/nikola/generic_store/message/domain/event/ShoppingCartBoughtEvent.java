package hristovski.nikola.generic_store.message.domain.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import hristovski.nikola.common.shared.domain.model.shipping.value.Address;
import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCartId;
import hristovski.nikola.generic_store.base.domain.DomainEvent;
import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import hristovski.nikola.generic_store.base.domain.serialization.JsonSerialization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

import static hristovski.nikola.common.shared.domain.constants.Topic.TOPIC_SHOPPING_CART_BOUGHT;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartBoughtEvent implements DomainEvent {
    private DomainObjectId id;
    private ShoppingCartId shoppingCartId;
    private Address shippingAddress;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant occurredOn;

    public ShoppingCartBoughtEvent(ShoppingCartId shoppingCartId, Address address) {
        this.id = DomainObjectId.randomId(DomainObjectId.class);
        this.shoppingCartId = shoppingCartId;
        this.shippingAddress = address;
        this.occurredOn = Instant.now();
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }

    @Override
    public String topic() {
        return TOPIC_SHOPPING_CART_BOUGHT;
    }

    @Override
    public DomainObjectId id() {
        return id;
    }

    @Override
    public String toJson() {
        return JsonSerialization.serialize(this);
    }
}
