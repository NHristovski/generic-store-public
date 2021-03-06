package hristovski.nikola.product.domain.remote.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import hristovski.nikola.common.shared.domain.exception.RestRequestException;
import hristovski.nikola.common.shared.domain.model.inventory.StockResponseElement;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.generic_store.message.domain.rest.inventory.request.StockRequest;
import hristovski.nikola.generic_store.message.domain.rest.inventory.response.StockResponse;
import hristovski.nikola.product.application.configuration.ProductProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    public static final boolean SECURE = false;
    private final ProductProperties productProperties;
    private final EurekaClient discoveryClient;
    private final RestTemplate restTemplate;

    @Override
    public Map<ProductId, StockResponseElement> getProductStocks(List<ProductId> productIds)
            throws RestRequestException {
        try {
//            InstanceInfo inventoryServiceInfo = discoveryClient
//                    .getNextServerFromEureka(productProperties.getInventoryService(), SECURE);

            String url = "http://inventory-service/stock";

            log.info("Sending request to {}", url);

            StockResponse response = restTemplate
                    .postForObject(url, StockRequest.builder()
                                    .products(productIds).build(),
                            StockResponse.class);

            return extractStocksFromResponse(response);

        } catch (Exception ex) {
            throw new RestRequestException("Failed to make REST request to the Inventory Service", ex);
        }
    }

    private Map<ProductId, StockResponseElement> extractStocksFromResponse(StockResponse response) {

        if (response == null || response.getStocks() == null) {
            log.error(
                    "Failed to obtain the product stocks from the Inventory Service"
            );
            throw new NullPointerException("The response or the stocks are null!");
        }

        return response.getStocks();
    }

}
