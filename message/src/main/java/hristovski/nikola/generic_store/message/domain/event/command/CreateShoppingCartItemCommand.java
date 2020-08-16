package hristovski.nikola.generic_store.message.domain.event.command;

import com.fasterxml.jackson.annotation.JsonFormat;
import hristovski.nikola.common.shared.domain.model.all.value.Money;
import hristovski.nikola.common.shared.domain.model.all.value.Name;
import hristovski.nikola.common.shared.domain.model.all.value.Quantity;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.generic_store.base.domain.DomainEvent;
import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import hristovski.nikola.generic_store.base.domain.serialization.JsonSerialization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

import static hristovski.nikola.common.shared.domain.constants.Topic.TOPIC_CREATE_SHOPPING_CART_ITEM;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateShoppingCartItemCommand implements DomainEvent {

    private DomainObjectId id;
    private ProductId productId;
    private ApplicationUserId applicationUserId;
    private Quantity quantity;
    private Money price;
    private Name productName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant occurredOn;

    public CreateShoppingCartItemCommand(ProductId productId, ApplicationUserId applicationUserId,
                                         Quantity quantity, Money price, Name productName) {
        this.id = DomainObjectId.randomId(DomainObjectId.class);
        this.productId = productId;
        this.applicationUserId = applicationUserId;
        this.quantity = quantity;
        this.price = price;
        this.productName = productName;
        this.occurredOn = Instant.now();
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }

    @Override
    public String topic() {
        return TOPIC_CREATE_SHOPPING_CART_ITEM;
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

