package hristovski.nikola.generic_store.shipping.application.rest.port;

import hristovski.nikola.common.shared.domain.model.shipping.ShippingId;
import hristovski.nikola.common.shared.domain.model.shipping.value.ShippingStatus;
import hristovski.nikola.generic_store.message.domain.rest.shipping.response.ShippingStatusChangedResponse;
import hristovski.nikola.generic_store.shipping.domain.service.ShippingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ShippingController {

    private final ShippingService shippingService;

    @PostMapping("/pickUp/{shippingId}")
    public ResponseEntity<ShippingStatusChangedResponse> pickUpOrder(@PathVariable String shippingId) {
        shippingService.pickUpOrder(new ShippingId(shippingId));
        return ResponseEntity.ok(
                ShippingStatusChangedResponse.builder()
                        .newStatus(ShippingStatus.DELIVERING)
                        .build()
        );
    }

    @PostMapping("/delivered/{shippingId}")
    public ResponseEntity<ShippingStatusChangedResponse> orderDelivered(@PathVariable String shippingId) {
        shippingService.orderDelivered(new ShippingId(shippingId));
        return ResponseEntity.ok(
                ShippingStatusChangedResponse.builder()
                        .newStatus(ShippingStatus.DELIVERED)
                        .build()
        );
    }
}
