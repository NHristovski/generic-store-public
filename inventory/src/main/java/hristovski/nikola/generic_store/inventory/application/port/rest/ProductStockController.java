package hristovski.nikola.generic_store.inventory.application.port.rest;

import hristovski.nikola.common.shared.domain.constants.Headers;
import hristovski.nikola.common.shared.domain.model.all.value.Quantity;
import hristovski.nikola.common.shared.domain.model.inventory.StockResponseElement;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.generic_store.base.domain.DomainEventPublisher;
import hristovski.nikola.generic_store.inventory.domain.service.ProductStockService;
import hristovski.nikola.generic_store.message.domain.event.command.CreateShoppingCartItemCommand;
import hristovski.nikola.generic_store.message.domain.rest.inventory.request.CheckStockRequest;
import hristovski.nikola.generic_store.message.domain.rest.inventory.request.DecrementStockResponse;
import hristovski.nikola.generic_store.message.domain.rest.inventory.request.RestockRequest;
import hristovski.nikola.generic_store.message.domain.rest.inventory.request.StockRequest;
import hristovski.nikola.generic_store.message.domain.rest.inventory.response.CheckStockResponse;
import hristovski.nikola.generic_store.message.domain.rest.inventory.response.RestockResponse;
import hristovski.nikola.generic_store.message.domain.rest.inventory.response.StockResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
public class ProductStockController {

    private final ProductStockService productStockService;
    private final DomainEventPublisher domainEventPublisher;

    @PostMapping("/stock")
    public ResponseEntity<StockResponse> stock(@Valid @RequestBody StockRequest stockRequest) {
        log.info("Called stock with request {}", stockRequest);
        Map<ProductId, StockResponseElement> stocks = productStockService.findStocks(stockRequest.getProducts());
        log.info("return {}", stocks);
        return ResponseEntity.ok(
                StockResponse.builder().stocks(
                        stocks
                ).build()
        );
    }

    @PostMapping("/decrement_stock/{productId}")
    public ResponseEntity<DecrementStockResponse> decrementStock(@PathVariable String productId) {

        log.info("Called decrementStock for product {}", productId);

        Boolean success = productStockService.decrementStock(new ProductId(productId));

        return ResponseEntity.ok(
                DecrementStockResponse.builder().success(
                        success
                ).build()
        );
    }

    @PutMapping("/restock/{productId}")
    public ResponseEntity<RestockResponse> restock(@Valid @RequestBody RestockRequest restockRequest,
                                                   @PathVariable String productId) {
        log.info("Called restock with request {}", restockRequest);

        ProductId id = new ProductId(restockRequest.getProductId());

        productStockService.restock(id, restockRequest.getStock());

        return ResponseEntity.ok(
                RestockResponse.builder().productId(id).stock(restockRequest.getStock()).build()
        );
    }

    @PostMapping("/check_stock/{productId}")
    public ResponseEntity<CheckStockResponse> checkStock(@Valid @RequestBody CheckStockRequest checkStockRequest,
                                                         @PathVariable String productId,
                                                         HttpServletRequest httpRequest) {

        String userId = httpRequest.getHeader(Headers.USER_ID);

        log.info("Called checkStock with request {}", checkStockRequest);

        boolean success = productStockService.updateStock(checkStockRequest.getProductId(), checkStockRequest.getStock());

        if (success) {
            domainEventPublisher.publish(
                    new CreateShoppingCartItemCommand(
                            checkStockRequest.getProductId(),
                            new ApplicationUserId(userId),
                            new Quantity(checkStockRequest.getStock()),
                            checkStockRequest.getPrice(),
                            checkStockRequest.getProductName()
                    )
            );
        }

        Quantity stock = productStockService.findByProductId(checkStockRequest.getProductId())
                .getStock();

        log.info("The quantity for product id {} is {}", checkStockRequest.getProductId().getId(),
                stock);
        return ResponseEntity.ok(
                CheckStockResponse.builder()
                        .success(success)
                        .stock(stock)
                        .build()
        );
    }
}
