package hristovski.nikola.generic_store.message.domain.rest.inventory.request;

import hristovski.nikola.common.shared.domain.model.all.value.Quantity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RestockRequest {

    @NotNull
    private String productId;

    @NotNull
    private Quantity stock;
}
