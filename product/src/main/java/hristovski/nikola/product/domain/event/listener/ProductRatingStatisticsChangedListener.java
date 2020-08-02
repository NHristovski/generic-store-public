package hristovski.nikola.product.domain.event.listener;

import hristovski.nikola.generic_store.base.domain.DomainEvent;
import hristovski.nikola.product.domain.event.ProductRatingStatisticsChangedEvent;
import hristovski.nikola.product.domain.model.product.ProductEntity;
import hristovski.nikola.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static hristovski.nikola.common.shared.domain.constants.Group.GROUP_ID_PRODUCT;
import static hristovski.nikola.common.shared.domain.constants.Topic.TOPIC_PRODUCT_RATING_STATISTICS_CHANGED;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductRatingStatisticsChangedListener {

    private final ProductRepository productRepository;

//    @KafkaListener(topics = TOPIC_PRODUCT_RATING_STATISTICS_CHANGED, groupId = GROUP_ID_PRODUCT)
//    public void consumeProductRatingStatisticsChangedEvent(String jsonMessage) {
//        try {
//            ProductRatingStatisticsChangedEvent event =
//                    DomainEvent.fromJson(jsonMessage, ProductRatingStatisticsChangedEvent.class);
//
//            log.info("Got event {}", jsonMessage);
//
//            ProductEntity productEntity = productRepository.findByDeletedFalseAndIdEquals(event.getProductId())
//                    .orElseThrow(() -> new RuntimeException(
//                            "Product with id " + event.getId().getId() + " does not exist")
//                    );
//
//
//            productEntity.setRatingStatistics(event.getRatingStatistics());
//
//            log.info("Saving product {}", productEntity);
//            productRepository.save(productEntity);
//        } catch (Exception exception) {
//            log.error("Failed to translate the domain event from json string: {}",
//                    jsonMessage, exception);
//        }
//    }
}
