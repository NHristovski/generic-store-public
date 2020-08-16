package hristovski.nikola.generic_store.message.domain.rest.inventory.request;

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
public class DecrementStockResponse {
    @NotNull
    private Boolean success;
}
