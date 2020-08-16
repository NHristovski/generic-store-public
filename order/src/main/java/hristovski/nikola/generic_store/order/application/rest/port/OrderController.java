package hristovski.nikola.generic_store.order.application.rest.port;

import hristovski.nikola.common.shared.domain.model.shipping.OrderId;
import hristovski.nikola.common.shared.domain.model.shipping.value.ShippingStatus;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.generic_store.message.domain.rest.order.response.GetOrderStatusResponse;
import hristovski.nikola.generic_store.message.domain.rest.order.response.GetOrdersResponse;
import hristovski.nikola.generic_store.message.domain.rest.shipping.response.ShippingStatusChangedResponse;
import hristovski.nikola.generic_store.order.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static hristovski.nikola.common.shared.domain.constants.Headers.USER_ID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @GetMapping()
    public ResponseEntity<GetOrdersResponse> getOrdersResponse(HttpServletRequest httpRequest) {

        log.info("Got get orders request");

        String applicationUserId = httpRequest.getHeader(USER_ID);

        return ResponseEntity.ok(GetOrdersResponse.builder()
                .orders(
                        orderService
                                .getOrdersForUser(new ApplicationUserId(applicationUserId))
                ).build()
        );
    }

    @GetMapping("/admin/all")
    public ResponseEntity<GetOrdersResponse> getOrdersResponse() {

        log.info("Got get all orders request");

        return ResponseEntity.ok(GetOrdersResponse.builder()
                .orders(
                        orderService.getAllOrders()
                ).build()
        );
    }

    @GetMapping("/admin/search/{query}")
    public ResponseEntity<GetOrdersResponse> search(@PathVariable String query) {
        log.info("Got search orders request with query {}", query);

        return ResponseEntity.ok(GetOrdersResponse.builder()
                .orders(
                        orderService.searchOrders(query)
                ).build()
        );
    }

    @GetMapping("/{orderId}/status")
    public ResponseEntity<GetOrderStatusResponse> getOrderStatusResponse(@PathVariable String orderId) {

        log.info("Got get order status request for orderId {}", orderId);

        return ResponseEntity.ok(GetOrderStatusResponse.builder()
                .status(
                        orderService
                                .getOrderStatus(new OrderId(orderId))
                ).build()
        );
    }

    @PostMapping("/ready/{orderId}")
    public ResponseEntity<ShippingStatusChangedResponse> orderReady(@PathVariable String orderId) {
        orderService.orderReady(new OrderId(orderId));
        return ResponseEntity.ok(
                ShippingStatusChangedResponse.builder()
                        .newStatus(ShippingStatus.READY)
                        .build()
        );
    }

    @PostMapping("/pickUp/{orderId}")
    public ResponseEntity<ShippingStatusChangedResponse> pickUpOrder(@PathVariable String orderId) {
        orderService.pickUpOrder(new OrderId(orderId));
        return ResponseEntity.ok(
                ShippingStatusChangedResponse.builder()
                        .newStatus(ShippingStatus.DELIVERING)
                        .build()
        );
    }

    @PostMapping("/delivered/{orderId}")
    public ResponseEntity<ShippingStatusChangedResponse> orderDelivered(@PathVariable String orderId) {
        orderService.orderDelivered(new OrderId(orderId));
        return ResponseEntity.ok(
                ShippingStatusChangedResponse.builder()
                        .newStatus(ShippingStatus.DELIVERED)
                        .build()
        );
    }


}
