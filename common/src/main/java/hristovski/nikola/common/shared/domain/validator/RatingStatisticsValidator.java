package hristovski.nikola.common.shared.domain.validator;

import hristovski.nikola.common.shared.domain.model.product.value.RatingStatistics;

public class RatingStatisticsValidator {
    public static void requireValid(RatingStatistics ratingStatistics) throws IllegalArgumentException {
        Validators.requireNonNegative(ratingStatistics.getTotalRatings());
        Validators.requireNonNegative(ratingStatistics.getAverageRating());
    }
}
