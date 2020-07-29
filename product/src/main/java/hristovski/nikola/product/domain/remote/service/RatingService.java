package hristovski.nikola.product.domain.remote.service;

import hristovski.nikola.common.shared.domain.exception.RestRequestException;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;

public interface RatingService {
    Integer getCurrentRating(ProductId productId, ApplicationUserId userId)
            throws RestRequestException;
}
