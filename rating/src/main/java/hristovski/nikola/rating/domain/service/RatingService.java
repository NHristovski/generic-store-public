package hristovski.nikola.rating.domain.service;

import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.rating.domain.model.RatingEntity;

public interface RatingService {

    RatingEntity getCurrentRating(ProductId productId, ApplicationUserId userId);

    Double getAverageRating(ProductId productId);

    Long getTotalRating(ProductId productId);

    RatingEntity rate(ProductId productId, ApplicationUserId userId, Integer rating);
}
