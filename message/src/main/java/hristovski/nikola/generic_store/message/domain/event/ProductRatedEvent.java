package hristovski.nikola.generic_store.message.domain.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.generic_store.base.domain.DomainEvent;
import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import hristovski.nikola.generic_store.base.domain.serialization.JsonSerialization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

import static hristovski.nikola.common.shared.domain.constants.Topic.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRatedEvent implements DomainEvent {

    private DomainObjectId id;
    private ProductId productId;
    private Integer rating;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant occurredOn;

    public ProductRatedEvent(ProductId productId, Integer rating) {
        this.id = DomainObjectId.randomId(DomainObjectId.class);
        this.productId = productId;
        this.rating = rating;
        this.occurredOn = Instant.now();
    }

    @Override
    public Instant occurredOn() {
        return this.occurredOn;
    }

    @Override
    public String topic() {
        return TOPIC_PRODUCT_RATINGS;
    }

    @Override
    public DomainObjectId id() {
        return this.id;
    }

    @Override
    public String toJson() {
        return JsonSerialization.serialize(this);
    }

}
