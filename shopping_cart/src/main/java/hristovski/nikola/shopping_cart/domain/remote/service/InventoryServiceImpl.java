package hristovski.nikola.shopping_cart.domain.remote.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import hristovski.nikola.common.shared.domain.exception.RestRequestException;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.generic_store.message.domain.rest.inventory.request.DecrementStockResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    public static final boolean SECURE = false;
    private final EurekaClient discoveryClient;
    private final RestTemplate restTemplate;

    @Override
    public boolean decrementStock(ProductId productId) throws RestRequestException {
        try {
            InstanceInfo inventoryServiceInfo = discoveryClient
                    .getNextServerFromEureka("inventory-service", SECURE);

            String url = buildRequestUrl(inventoryServiceInfo, productId);

            log.info("Sending request to {}", url);

            DecrementStockResponse response = restTemplate.postForObject(url, null,
                    DecrementStockResponse.class);

            return extractStocksFromResponse(response);

        } catch (Exception ex) {
            throw new RestRequestException("Failed to make REST request to the Inventory Service", ex);
        }
    }

    private String buildRequestUrl(InstanceInfo inventoryServiceInfo, ProductId productId) {
        return inventoryServiceInfo.getHomePageUrl() + "decrement_stock/" + productId.getId();
    }

    private Boolean extractStocksFromResponse(DecrementStockResponse response) {

        if (response == null || response.getSuccess() == null) {
            log.error(
                    "Failed to decrement the product stocks in the Inventory Service"
            );
            throw new NullPointerException("The response or the status is null!");
        }

        return response.getSuccess();
    }
}
