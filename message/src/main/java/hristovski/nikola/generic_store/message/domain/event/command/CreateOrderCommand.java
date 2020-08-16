package hristovski.nikola.generic_store.message.domain.event.command;

import com.fasterxml.jackson.annotation.JsonFormat;
import hristovski.nikola.common.shared.domain.model.all.value.Money;
import hristovski.nikola.common.shared.domain.model.shipping.value.Address;
import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCartItem;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.generic_store.base.domain.DomainEvent;
import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import hristovski.nikola.generic_store.base.domain.serialization.JsonSerialization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

import static hristovski.nikola.common.shared.domain.constants.Topic.TOPIC_CREATE_ORDER;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderCommand implements DomainEvent {

    private DomainObjectId id;
    private ApplicationUserId applicationUserId;
    private List<ShoppingCartItem> shoppingCartItems;
    private Address address;
    private Money price;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant occurredOn;

    public CreateOrderCommand(ApplicationUserId applicationUserId, List<ShoppingCartItem> shoppingCartItems,
                              Money price, Address shippingAddress) {
        this.id = DomainObjectId.randomId(DomainObjectId.class);
        this.address = shippingAddress;
        this.applicationUserId = applicationUserId;
        this.price = price;
        this.shoppingCartItems = shoppingCartItems;
        this.occurredOn = Instant.now();
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }

    @Override
    public String topic() {
        return TOPIC_CREATE_ORDER;
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

