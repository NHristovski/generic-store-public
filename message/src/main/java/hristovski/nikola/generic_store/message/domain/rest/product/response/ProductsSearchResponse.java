package hristovski.nikola.generic_store.message.domain.rest.product.response;

import hristovski.nikola.common.shared.domain.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductsSearchResponse {
    List<Product> products;
}
