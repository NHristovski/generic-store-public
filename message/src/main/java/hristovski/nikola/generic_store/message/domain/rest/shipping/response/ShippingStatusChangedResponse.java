package hristovski.nikola.generic_store.message.domain.rest.shipping.response;

import hristovski.nikola.common.shared.domain.model.shipping.value.ShippingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShippingStatusChangedResponse {
    ShippingStatus newStatus;
}
