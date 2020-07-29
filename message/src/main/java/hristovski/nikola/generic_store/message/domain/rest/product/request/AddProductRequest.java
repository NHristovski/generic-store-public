package hristovski.nikola.generic_store.message.domain.rest.product.request;

import hristovski.nikola.common.shared.domain.model.category.Category;
import hristovski.nikola.common.shared.domain.model.product.value.ImageURL;
import hristovski.nikola.common.shared.domain.model.product.value.ProductInformation;
import hristovski.nikola.common.shared.domain.model.all.value.Money;
import hristovski.nikola.common.shared.domain.model.all.value.Quantity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddProductRequest {

    @NonNull
    private ImageURL imageLocation;

    @NonNull
    private ProductInformation information;

    @NonNull
    private Money price;

    @NonNull
    private Quantity stock;

    @NonNull
    private Set<Category> categories;
}

