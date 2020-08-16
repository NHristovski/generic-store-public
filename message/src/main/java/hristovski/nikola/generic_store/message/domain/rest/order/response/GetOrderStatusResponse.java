package hristovski.nikola.generic_store.message.domain.rest.order.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetOrderStatusResponse {
    private Integer status;
}
