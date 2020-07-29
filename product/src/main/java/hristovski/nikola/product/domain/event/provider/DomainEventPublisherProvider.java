package hristovski.nikola.product.domain.event.provider;

import hristovski.nikola.generic_store.base.domain.DomainEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DomainEventPublisherProvider {
    private static DomainEventPublisher domainEventPublisher;

    public DomainEventPublisherProvider(DomainEventPublisher publisher) {
        log.info("the publisher is: {}", publisher);
        DomainEventPublisherProvider.domainEventPublisher = publisher;
    }

    public static DomainEventPublisher getInstance() {
        return domainEventPublisher;
    }
}
