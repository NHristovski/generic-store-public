package hristovski.nikola.shopping_cart.domain.event.listener;

import hristovski.nikola.generic_store.base.domain.DomainEvent;
import hristovski.nikola.generic_store.message.domain.event.CreateShoppingCartItemCommand;
import hristovski.nikola.generic_store.message.domain.event.ProductCreatedEvent;
import hristovski.nikola.shopping_cart.domain.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static hristovski.nikola.common.shared.domain.constants.Group.GROUP_ID_INVENTORY;
import static hristovski.nikola.common.shared.domain.constants.Group.GROUP_ID_SHOPPING_CART;
import static hristovski.nikola.common.shared.domain.constants.Topic.TOPIC_CREATE_SHOPPING_CART_ITEM;
import static hristovski.nikola.common.shared.domain.constants.Topic.TOPIC_PRODUCT_CREATED;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateShoppingCartItemCommandListener {

    private final ShoppingCartService shoppingCartService;

    @KafkaListener(topics = TOPIC_CREATE_SHOPPING_CART_ITEM, groupId = GROUP_ID_SHOPPING_CART)
    public void consumeProductCreatedEvent(String jsonMessage) {
        log.info("Got create shopping cart item command {}", jsonMessage);
        try {
            CreateShoppingCartItemCommand event =
                    DomainEvent.fromJson(jsonMessage, CreateShoppingCartItemCommand.class);

            shoppingCartService.addProductToShoppingCart(
                    event.getProductId(),
                    event.getApplicationUserId(),
                    event.getQuantity(),
                    event.getPrice(),
                    event.getProductName()
            );
        } catch (Exception exception) {
            log.error("Failed to translate the domain event from json string: {}",
                    jsonMessage, exception);
        }
    }
}
