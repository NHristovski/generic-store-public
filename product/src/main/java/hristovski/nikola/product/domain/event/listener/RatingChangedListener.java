package hristovski.nikola.product.domain.event.listener;

import hristovski.nikola.generic_store.base.domain.DomainEvent;
import hristovski.nikola.generic_store.message.domain.event.ProductRatedEvent;
import hristovski.nikola.product.domain.exception.ProductNotFoundException;
import hristovski.nikola.product.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static hristovski.nikola.common.shared.domain.constants.Group.GROUP_ID_PRODUCT;
import static hristovski.nikola.common.shared.domain.constants.Topic.TOPIC_PRODUCT_RATINGS;

@Service
@RequiredArgsConstructor
@Slf4j
public class RatingChangedListener {

    private final ProductService productService;

    @KafkaListener(topics = TOPIC_PRODUCT_RATINGS, groupId = GROUP_ID_PRODUCT)
    public void consumeRatingChangedEvent(String jsonMessage) {
        try {
            ProductRatedEvent event = DomainEvent.fromJson(jsonMessage, ProductRatedEvent.class);

            productService.adjustProductRatingStatistics(
                    event.getProductId(), event.getRating()
            );
        }catch (ProductNotFoundException exception){
            log.error("Failed to update product", exception);
        }
        catch (Exception exception) {
            log.error("Failed to translate the domain event from json string: {}",
                    jsonMessage, exception);
        }
    }
}
