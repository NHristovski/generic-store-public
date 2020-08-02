package hristovski.nikola.rating.domain.service;

import hristovski.nikola.common.shared.domain.factory.rating.RatingResponseElementFactory;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.common.shared.domain.model.rating.RatingResponseElement;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.generic_store.base.domain.DomainEventPublisher;
import hristovski.nikola.generic_store.message.domain.event.ProductRatedEvent;
import hristovski.nikola.generic_store.message.domain.event.ProductRatingChangedEvent;
import hristovski.nikola.rating.domain.model.RatingEntity;
import hristovski.nikola.rating.domain.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final DomainEventPublisher eventPublisher;

    @Override
    public Integer getCurrentRating(ProductId productId, ApplicationUserId userId) {
        try {
            return ratingRepository.getRatingByProductIdAndApplicationUserId(productId, userId)
                    .getRating();
        } catch (Exception exception) {
            return 0;
        }
    }

    @Override
    public Double getAverageRating(ProductId productId) {
        return ratingRepository.queryAllByProductId(productId)
                .stream()
                .mapToDouble(RatingEntity::getRating)
                .average()
                .orElseThrow(() -> new RuntimeException(
                        "Failed to obtain average rating for product " + productId.getId())
                );
    }

    @Override
    public Long getTotalRating(ProductId productId) {
        return ratingRepository.countAllByProductId(productId);
    }

    @Override
    public RatingEntity rate(ProductId productId, ApplicationUserId userId, Integer rating) {

        RatingEntity ratingEntity = ratingRepository.getRatingByProductIdAndApplicationUserId(productId, userId);
        if (ratingEntity != null) {

            log.info("rating for product id {} from user id {} already exists"
                    , productId.getId(), userId.getId());

            Integer oldRating = ratingEntity.getRating();
            log.info("Old rating {}", oldRating);

            ratingEntity.changeRating(rating);

            RatingEntity savedRatingEntity = ratingRepository.save(ratingEntity);

            eventPublisher.publish(
                    new ProductRatingChangedEvent(productId, oldRating, rating)
            );

            return savedRatingEntity;
        } else {
            ratingEntity = new RatingEntity(productId, userId, rating);
            RatingEntity savedRatingEntity = ratingRepository.save(ratingEntity);

            eventPublisher.publish(
                    new ProductRatedEvent(productId, rating)
            );

            return savedRatingEntity;
        }
    }

    @Override
    public Map<ProductId, RatingResponseElement> findRatings(List<ProductId> products, ApplicationUserId userId) {
        Map<ProductId, RatingResponseElement> result = new HashMap<>();

        for (ProductId productId : products) {
            try {
                Integer rating = ratingRepository.getRatingByProductIdAndApplicationUserId(productId, userId)
                        .getRating();

                result.put(productId, RatingResponseElementFactory.fromRating(rating));
            } catch (Exception exception) {
                result.put(productId, RatingResponseElementFactory.fromError(
                        "Failed to find ratings for product " + productId + ". Exception message: " +
                                exception.getMessage()
                        )
                );
            }
        }

        return result;
    }

//    @Override
//    public ProductResponse getCurrentRating(ProductEntity productEntity, String username) {
//        RatingEntity ratingEntity = ratingRepository.getRatingByProductIdAndApplicationUserId(productEntity, username);
//        List<RatingEntity> ratingEntities = ratingRepository.queryAllByProductId(productEntity);
//
//        Double average = ratingEntities
//                .stream()
//                .mapToDouble(RatingEntity::getRating)
//                .average().orElse(0d);
//
//        return new ProductResponse(productEntity, ratingEntity.getRating(),average, ratingEntities.size());
//    }
//
//    @Override
//    public Double getAverageRating(ProductEntity productEntity) {
//        List<RatingEntity> ratingEntities = ratingRepository.queryAllByProductId(productEntity);
//
//        return ratingEntities
//                .stream()
//                .mapToDouble(RatingEntity::getRating)
//                .average().orElse(0d);
//    }
//
//    @Override
//    public Integer getTotalRating(ProductEntity productEntity) {
//        List<RatingEntity> ratingEntities = ratingRepository.queryAllByProductId(productEntity);
//        return ratingEntities.size();
//    }
//
//    @Override
//    public ProductResponse rate(ProductEntity productEntity, Integer ratingScore, String username) {
//
//        try {
//            RatingEntity oldRatingEntity = ratingRepository.findByProductAndUsername(productEntity, username);
//            if (oldRatingEntity != null){
//                oldRatingEntity.setRating(ratingScore);
//                ratingRepository.save(oldRatingEntity);
//            }else{
//                RatingEntity ratingEntity = new RatingEntity();
//                ratingEntity.setProduct(productEntity);
//                ratingEntity.setRating(ratingScore);
//                ratingEntity.setUsername(username);
//                ratingRepository.save(ratingEntity);
//            }
//        }catch (Exception ex) {
//
//            RatingEntity ratingEntity = new RatingEntity();
//            ratingEntity.setProduct(productEntity);
//            ratingEntity.setRating(ratingScore);
//            ratingEntity.setUsername(username);
//            ratingRepository.save(ratingEntity);
//        }
//
//        return getCurrentRating(productEntity,username);
//    }
}
