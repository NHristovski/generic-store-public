package hristovski.nikola.common.shared.domain.factory.rating;

import hristovski.nikola.common.shared.domain.model.all.value.Error;
import hristovski.nikola.common.shared.domain.model.all.value.Quantity;
import hristovski.nikola.common.shared.domain.model.inventory.StockResponseElement;
import hristovski.nikola.common.shared.domain.model.rating.RatingResponseElement;

public class RatingResponseElementFactory {

    public static RatingResponseElement fromError(String errorMessage) {
        return RatingResponseElement.builder()
                .error(new Error(errorMessage, true))
                .build();
    }

    public static RatingResponseElement fromRating(Integer rating) {
        return RatingResponseElement.builder()
                .error(new Error(null, false))
                .rating(rating)
                .build();
    }
}
