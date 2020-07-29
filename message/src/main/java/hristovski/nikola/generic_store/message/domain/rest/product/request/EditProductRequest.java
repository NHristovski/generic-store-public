package hristovski.nikola.generic_store.message.domain.rest.product.request;

import hristovski.nikola.common.shared.domain.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditProductRequest {
    @NonNull
    private Product product;
}

