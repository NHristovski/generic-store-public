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

import static hristovski.nikola.common.shared.domain.constants.Topic.TOPIC_PRODUCT_RATING_CHANGED;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRatingChangedEvent implements DomainEvent {
    private DomainObjectId id;
    private ProductId productId;
    private Integer oldRating;
    private Integer newRating;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant occurredOn;

    public ProductRatingChangedEvent(ProductId productId, Integer oldRating, Integer newRating) {
        this.id = DomainObjectId.randomId(DomainObjectId.class);
        this.productId = productId;
        this.oldRating = oldRating;
        this.newRating = newRating;
        this.occurredOn = Instant.now();
    }

    @Override
    public Instant occurredOn() {
        return this.occurredOn;
    }

    @Override
    public String topic() {
        return TOPIC_PRODUCT_RATING_CHANGED;
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
