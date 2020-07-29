package hristovski.nikola.generic_store.base.domain;

import hristovski.nikola.generic_store.base.domain.serialization.JsonSerialization;
import org.springframework.lang.NonNull;

import java.time.Instant;

public interface DomainEvent extends DomainObject {

    @NonNull
    Instant occurredOn();

    @NonNull
    String topic();

    @NonNull
    DomainObjectId id();

    @NonNull
    String toJson();

    static <EVENT extends DomainEvent> EVENT fromJson(String json, Class<EVENT> eventClass){
        return JsonSerialization.deserialize(json, eventClass);
    }
}
