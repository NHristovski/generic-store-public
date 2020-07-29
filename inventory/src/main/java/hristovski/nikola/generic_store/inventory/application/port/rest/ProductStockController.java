package hristovski.nikola.generic_store.inventory.application.port.rest;

import hristovski.nikola.generic_store.inventory.domain.service.ProductStockService;
import hristovski.nikola.generic_store.message.domain.rest.inventory.request.StockRequest;
import hristovski.nikola.generic_store.message.domain.rest.inventory.response.StockResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@Slf4j
public class ProductStockController {

    private final ProductStockService productStockService;

    @PostMapping("/stock")
    public ResponseEntity<StockResponse> stock(@Valid @RequestBody StockRequest stockRequest) {
        return ResponseEntity.ok(
                StockResponse.builder().stocks(
                                productStockService.findStocks(stockRequest.getProducts())
                        ).build()
        );
    }
}
