package hristovski.nikola.generic_store.message.domain.rest.product.response;

import hristovski.nikola.common.shared.domain.model.product.PersonalizedProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPersonalizedProductsPageResponse {
    private List<PersonalizedProduct> personalizedProducts;
    private Integer maxPages;
}
