package hristovski.nikola.generic_store.message.domain.event.command;

import com.fasterxml.jackson.annotation.JsonFormat;
import hristovski.nikola.common.shared.domain.model.inventory.value.ProductReservation;
import hristovski.nikola.generic_store.base.domain.DomainEvent;
import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import hristovski.nikola.generic_store.base.domain.serialization.JsonSerialization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

import static hristovski.nikola.common.shared.domain.constants.Topic.TOPIC_MAKE_PRODUCT_RESERVATIONS;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReserveProductsCommand implements DomainEvent {

    private DomainObjectId id;
    List<ProductReservation> productReservations;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant occurredOn;

    public ReserveProductsCommand(List<ProductReservation> productReservations) {
        this.id = DomainObjectId.randomId(DomainObjectId.class);
        this.productReservations = productReservations;
        this.occurredOn = Instant.now();
    }


    @Override
    public Instant occurredOn() {
        return occurredOn;
    }

    @Override
    public String topic() {
        return TOPIC_MAKE_PRODUCT_RESERVATIONS;
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
