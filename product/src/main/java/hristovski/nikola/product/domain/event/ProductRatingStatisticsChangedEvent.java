package hristovski.nikola.product.domain.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.common.shared.domain.model.product.value.RatingStatistics;
import hristovski.nikola.generic_store.base.domain.DomainEvent;
import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import hristovski.nikola.generic_store.base.domain.serialization.JsonSerialization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.time.Instant;

import static hristovski.nikola.common.shared.domain.constants.Topic.TOPIC_PRODUCT_RATING_STATISTICS_CHANGED;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRatingStatisticsChangedEvent implements DomainEvent {

    private DomainObjectId id;
    private ProductId productId;
    private RatingStatistics ratingStatistics;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant occurredOn;
    private String topic;

    public ProductRatingStatisticsChangedEvent(ProductId productId,
                                               RatingStatistics ratingStatistics) {
        this.id = DomainObjectId.randomId(DomainObjectId.class);
        this.productId = productId;
        this.ratingStatistics = ratingStatistics;
        this.occurredOn = Instant.now();
        this.topic = TOPIC_PRODUCT_RATING_STATISTICS_CHANGED;
    }

    @Override
    @NonNull
    public Instant occurredOn() {
        return occurredOn;
    }

    @Override
    @NonNull
    public String topic() {
        return topic;
    }

    @Override
    @NonNull
    public DomainObjectId id() {
        return id;
    }

    @Override
    @NonNull
    public String toJson() {
        return JsonSerialization.serialize(this);
    }
}



