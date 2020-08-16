package hristovski.nikola.generic_store.message.domain.rest.order.response;

import hristovski.nikola.common.shared.domain.model.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetOrdersResponse {
    private List<Order> orders;
}
