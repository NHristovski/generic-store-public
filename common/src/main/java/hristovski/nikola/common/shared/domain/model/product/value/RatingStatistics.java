package hristovski.nikola.common.shared.domain.model.product.value;

import hristovski.nikola.generic_store.base.domain.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@Getter
public class RatingStatistics implements ValueObject {
    private final Long totalRatings;
    private final Double averageRating;

    public RatingStatistics() {
        this.totalRatings = 0L;
        this.averageRating = 0d;
    }

    public RatingStatistics addRating(Integer rating) {
        Double currentRating = totalRatings * averageRating;
        Double newRating = currentRating + rating;
        long newTotalRatings = totalRatings + 1;
        return new RatingStatistics(newTotalRatings, newRating / newTotalRatings);
    }

    public RatingStatistics removeRating(Integer rating) {
        if (totalRatings == 1) {
            return new RatingStatistics();
        }

        Double currentRating = totalRatings * averageRating;
        Double newRating = currentRating - rating;
        long newTotalRatings = totalRatings - 1;
        return new RatingStatistics(newTotalRatings, newRating / newTotalRatings);
    }
}



