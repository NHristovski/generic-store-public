package hristovski.nikola.generic_store.message.domain.rest.shopping_cart.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

// TODO Create response
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuantityRequest {
    @NonNull
    private Long id;
}
