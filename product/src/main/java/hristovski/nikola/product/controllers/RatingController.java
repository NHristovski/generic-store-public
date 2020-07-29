//package hristovski.nikola.product.controllers;
//
//import hristovski.nikola.common.shared.message.rating.request.RateRequest;
//import hristovski.nikola.common.shared.message.rating.response.RateResponse;
//import hristovski.nikola.common.shared.domain.model.product.ProductId;
//import hristovski.nikola.common.shared.domain.model.user.ApplicationUser;
//import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
//import hristovski.nikola.product.domain.model.rating.RatingEntity;
//import hristovski.nikola.product.domain.repository.RatingRepository;
//import hristovski.nikola.product.domain.service.RatingService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping("/ratings")
//@RequiredArgsConstructor
//@Slf4j
//public class RatingController {
//
//    private final RatingService ratingService;
//
//    @PostMapping("/rate")
//    public ResponseEntity<RateResponse> rateProduct(@Valid @RequestBody RateRequest rateRequest) {
//
//        ratingService.rate(
//                new ProductId(rateRequest.getProductId()),
//                new ApplicationUserId(rateRequest.getApplicationUserId()),
//                rateRequest.getRating()
//        );
//
//        return ResponseEntity.ok(RateResponse.builder().build());
//    }
//}
