package hristovski.nikola.generic_store.inventory.domain.event.listener;

import hristovski.nikola.generic_store.base.domain.DomainEvent;
import hristovski.nikola.generic_store.inventory.domain.service.ProductStockService;
import hristovski.nikola.generic_store.message.domain.event.command.FreeProductsReservationCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static hristovski.nikola.common.shared.domain.constants.Group.GROUP_ID_INVENTORY;
import static hristovski.nikola.common.shared.domain.constants.Topic.TOPIC_FREE_PRODUCT_RESERVATIONS;

@Service
@RequiredArgsConstructor
@Slf4j

public class FreeProductReservationListener {

    private final ProductStockService productStockService;

    @KafkaListener(topics = TOPIC_FREE_PRODUCT_RESERVATIONS, groupId = GROUP_ID_INVENTORY)
    public void consumeFreeProductReservationEvent(String jsonMessage) {
        log.info("Got FreeProductReservation event {}", jsonMessage);
        try {
            FreeProductsReservationCommand event = DomainEvent
                    .fromJson(jsonMessage, FreeProductsReservationCommand.class);

            productStockService.freeProductsReservation(
                    event.getProductReservations()
            );
        } catch (Exception exception) {
            log.error("Failed to translate the domain event from json string: {}",
                    jsonMessage, exception);
        }
    }
}
