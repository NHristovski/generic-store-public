package hristovski.nikola.generic_store.message.domain.rest.rating.request;

import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RatingRequest {

    @NotNull
    private List<ProductId> products;

    @NotNull
    private ApplicationUserId applicationUserId;
}
