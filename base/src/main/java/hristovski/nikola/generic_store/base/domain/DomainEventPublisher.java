package hristovski.nikola.generic_store.base.domain;

public interface DomainEventPublisher {

    void publish(DomainEvent event);
}
