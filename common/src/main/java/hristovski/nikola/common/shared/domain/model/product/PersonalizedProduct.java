package hristovski.nikola.common.shared.domain.model.product;

import hristovski.nikola.common.shared.domain.model.all.value.Money;
import hristovski.nikola.common.shared.domain.model.all.value.Quantity;
import hristovski.nikola.common.shared.domain.model.category.Category;
import hristovski.nikola.common.shared.domain.model.product.value.ImageURL;
import hristovski.nikola.common.shared.domain.model.product.value.ProductInformation;
import hristovski.nikola.common.shared.domain.model.product.value.RatingStatistics;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalizedProduct extends Product {
    private Integer currentUserRating;
    private Quantity stock;

    public PersonalizedProduct(ProductId productId, Long version, ImageURL imageLocation,
                               ProductInformation information, Instant createdOn,
                               Money price, Quantity stock, RatingStatistics ratingStatistics,
                               Set<Category> categories, Integer currentUserRating) {
        super(productId, version, imageLocation, information, createdOn, price, stock, ratingStatistics, categories);
        this.currentUserRating = currentUserRating;
    }
}
