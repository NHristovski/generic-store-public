package hristovski.nikola.rating.application.port.rest;

import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.generic_store.base.domain.DomainEventPublisher;
import hristovski.nikola.generic_store.message.domain.event.ProductRatedEvent;
import hristovski.nikola.generic_store.message.domain.rest.rating.request.RateRequest;
import hristovski.nikola.generic_store.message.domain.rest.rating.response.GetCurrentRatingResponse;
import hristovski.nikola.generic_store.message.domain.rest.rating.response.RateResponse;
import hristovski.nikola.rating.domain.service.RatingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
@Slf4j
public class RatingController {

    private final RatingService ratingService;
    private final DomainEventPublisher domainEventPublisher;

    @PostMapping("/rate")
    public ResponseEntity<RateResponse> rateProduct(@Valid @RequestBody RateRequest rateRequest) {

        ratingService.rate(
                new ProductId(rateRequest.getProductId()),
                new ApplicationUserId(rateRequest.getApplicationUserId()),
                rateRequest.getRating()
        );

        return ResponseEntity.ok(RateResponse.builder().build());
    }

    @GetMapping("/currentRating/{productId}/{userId}")
    public ResponseEntity<GetCurrentRatingResponse> getCurrentRating(
            @PathVariable String productId,
            @PathVariable String userId
    ) {
        return ResponseEntity.ok(
                GetCurrentRatingResponse.builder()
                        .rating(ratingService.getCurrentRating(
                                new ProductId(productId),
                                new ApplicationUserId(userId)
                        ).getRating())
                        .build()
        );
    }

    @GetMapping("/tk")
    public String testingKafka(){
        log.info("Inside tk");
        domainEventPublisher.publish(new ProductRatedEvent(
                new ProductId("testing"),
                5,
                "TEST_TOPIC"
        ));

        return "ok";
    }
}
