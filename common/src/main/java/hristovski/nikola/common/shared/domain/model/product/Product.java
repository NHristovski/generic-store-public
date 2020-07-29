package hristovski.nikola.common.shared.domain.model.product;

import hristovski.nikola.common.shared.domain.model.category.Category;
import hristovski.nikola.common.shared.domain.model.product.value.ImageURL;
import hristovski.nikola.common.shared.domain.model.product.value.ProductInformation;
import hristovski.nikola.common.shared.domain.model.product.value.RatingStatistics;
import hristovski.nikola.common.shared.domain.model.all.value.Money;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private ProductId productId;

    private Long version;

    private ImageURL imageLocation;

    private ProductInformation information;

    private Instant createdOn;

    private Money price;

//    private Quantity stock;

    private RatingStatistics ratingStatistics;

    private Set<Category> categories;
}
