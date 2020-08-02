package hristovski.nikola.generic_store.inventory.domain.event.listener;

import hristovski.nikola.generic_store.base.domain.DomainEvent;
import hristovski.nikola.generic_store.inventory.domain.service.ProductStockService;
import hristovski.nikola.generic_store.message.domain.event.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static hristovski.nikola.common.shared.domain.constants.Group.GROUP_ID_INVENTORY;
import static hristovski.nikola.common.shared.domain.constants.Topic.TOPIC_PRODUCT_CREATED;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductCreatedListener {

    private final ProductStockService productStockService;

    @KafkaListener(topics = TOPIC_PRODUCT_CREATED, groupId = GROUP_ID_INVENTORY)
    public void consumeProductCreatedEvent(String jsonMessage) {
        log.info("Got product created event {}", jsonMessage);
        try {
            ProductCreatedEvent event = DomainEvent.fromJson(jsonMessage, ProductCreatedEvent.class);

            productStockService.createProductStock(event.getProductId(), event.getStock());
        } catch (Exception exception) {
            log.error("Failed to translate the domain event from json string: {}",
                    jsonMessage, exception);
        }
    }
}
