package hristovski.nikola.generic_store.message.domain.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.generic_store.base.domain.DomainEvent;
import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import hristovski.nikola.generic_store.base.domain.serialization.JsonSerialization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

import static hristovski.nikola.common.shared.domain.constants.Topic.TOPIC_STRIPE_CUSTOMER_CREATED;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StripeCustomerRegisteredEvent implements DomainEvent {

    private DomainObjectId id;
    private ApplicationUserId applicationUserId;
    private String customerId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant occurredOn;

    public StripeCustomerRegisteredEvent(ApplicationUserId applicationUserId, String customerId) {
        this.id = DomainObjectId.randomId(DomainObjectId.class);
        this.applicationUserId = applicationUserId;
        this.customerId = customerId;
        this.occurredOn = Instant.now();
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }

    @Override
    public String topic() {
        return TOPIC_STRIPE_CUSTOMER_CREATED;
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
