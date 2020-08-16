package hristovski.nikola.rating.domain.service;

import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.common.shared.domain.model.rating.RatingResponseElement;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.rating.domain.persistance.entity.RatingEntity;

import java.util.List;
import java.util.Map;

public interface RatingService {

    Integer getCurrentRating(ProductId productId, ApplicationUserId userId);

    Double getAverageRating(ProductId productId);

    Long getTotalRating(ProductId productId);

    RatingEntity rate(ProductId productId, ApplicationUserId userId, Integer rating);

    Map<ProductId, RatingResponseElement> findRatings(List<ProductId> products, ApplicationUserId applicationUserId);
}
