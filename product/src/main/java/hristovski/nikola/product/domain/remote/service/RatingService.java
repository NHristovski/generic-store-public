package hristovski.nikola.product.domain.remote.service;

import hristovski.nikola.common.shared.domain.exception.RestRequestException;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.common.shared.domain.model.rating.RatingResponseElement;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;

import java.util.List;
import java.util.Map;

public interface RatingService {
    Integer getCurrentRating(ProductId productId, ApplicationUserId userId)
            throws RestRequestException;

    Map<ProductId, RatingResponseElement> findRatings(List<ProductId> collect, ApplicationUserId userId) throws RestRequestException;
}
