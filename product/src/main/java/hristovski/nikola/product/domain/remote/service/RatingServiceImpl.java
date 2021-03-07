package hristovski.nikola.product.domain.remote.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import hristovski.nikola.common.shared.domain.exception.RestRequestException;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.common.shared.domain.model.rating.RatingResponseElement;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.generic_store.message.domain.rest.rating.request.RatingRequest;
import hristovski.nikola.generic_store.message.domain.rest.rating.response.GetCurrentRatingResponse;
import hristovski.nikola.generic_store.message.domain.rest.rating.response.RatingResponse;
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
public class RatingServiceImpl implements RatingService {

    public static final boolean SECURE = false;
    private final ProductProperties productProperties;
    private final EurekaClient discoveryClient;
    private final RestTemplate restTemplate;

    @Override
    public Integer getCurrentRating(ProductId productId, ApplicationUserId userId)
            throws RestRequestException {
        try {
//            InstanceInfo ratingServiceInfo = discoveryClient
//                    .getNextServerFromEureka(productProperties.getRatingService(), SECURE);

            String url = buildRequestUrl(productId, userId);

            log.info("Sending request to {}", url);

            GetCurrentRatingResponse response = restTemplate
                    .getForObject(url, GetCurrentRatingResponse.class);

            return extractRatingFromResponse(productId, userId, response);

        } catch (Exception ex) {
            throw new RestRequestException("Failed to make REST request to the Rating Service", ex);
        }
    }

    @Override
    public Map<ProductId, RatingResponseElement> findRatings(List<ProductId> products, ApplicationUserId userId)
            throws RestRequestException {
        try {
//            InstanceInfo ratingServiceInfo = discoveryClient
//                    .getNextServerFromEureka(productProperties.getRatingService(), SECURE);

            String url = "http://rating-service/ratings/rating";

            log.info("Sending request to {}", url);

            RatingResponse response = restTemplate
                    .postForObject(url,
                            RatingRequest.builder()
                                    .applicationUserId(userId)
                                    .products(products)
                                    .build(),
                            RatingResponse.class);

            return extractRatingFromResponse(response);

        } catch (Exception ex) {
            throw new RestRequestException("Failed to make REST request to the Rating Service", ex);
        }
    }

    private Integer extractRatingFromResponse(ProductId productId, ApplicationUserId userId,
                                              GetCurrentRatingResponse response) {
        if (response == null || response.getRating() == null) {
            log.error(
                    "Failed to obtain the current rating for product {} from user {}"
                    , productId.getId(), userId.getId()
            );
            return 0;
        }

        return response.getRating();
    }

    private Map<ProductId, RatingResponseElement> extractRatingFromResponse(RatingResponse response) {
        if (response == null || response.getRatings() == null) {
            log.error("Failed to obtain the ratings");
            throw new RuntimeException("The response or ratings are null!");
        }

        return response.getRatings();
    }

    private String buildRequestUrl(ProductId productId, ApplicationUserId userId) {

        return "http://rating-service/" +
                "ratings/current_rating/" +
                productId.getId() +
                "/" +
                userId.getId();
    }

}
