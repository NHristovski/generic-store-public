package hristovski.nikola.common.shared.domain.model.inventory.value;

import hristovski.nikola.common.shared.domain.model.all.value.Quantity;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductReservation {
    private ProductId productId;
    private Quantity quantity;
}
