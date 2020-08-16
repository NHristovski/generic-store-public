package hristovski.nikola.shopping_cart.application.port.rest;

import hristovski.nikola.common.shared.domain.exception.RestRequestException;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCart;
import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCartId;
import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCartItemId;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.generic_store.message.domain.rest.shopping_cart.request.AddProductToShoppingCartRequest;
import hristovski.nikola.generic_store.message.domain.rest.shopping_cart.request.BuyRequest;
import hristovski.nikola.generic_store.message.domain.rest.shopping_cart.response.AddProductToShoppingCartResponse;
import hristovski.nikola.generic_store.message.domain.rest.shopping_cart.response.BuyResponse;
import hristovski.nikola.generic_store.message.domain.rest.shopping_cart.response.ChangeQuantityResponse;
import hristovski.nikola.generic_store.message.domain.rest.shopping_cart.response.DeleteShoppingCartItemResponse;
import hristovski.nikola.generic_store.message.domain.rest.shopping_cart.response.GetShoppingCardResponse;
import hristovski.nikola.generic_store.message.domain.rest.shopping_cart.response.GetShoppingCartHistoryResponse;
import hristovski.nikola.shopping_cart.application.port.exception.FailedToBuyException;
import hristovski.nikola.shopping_cart.application.port.exception.InsufficientQuantityException;
import hristovski.nikola.shopping_cart.application.port.exception.MaxQuantityReachedException;
import hristovski.nikola.shopping_cart.application.port.exception.MinQuantityReachedException;
import hristovski.nikola.shopping_cart.domain.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static hristovski.nikola.common.shared.domain.constants.Headers.USER_ID;

@RestController
@RequestMapping("/shopping_cart")
@RequiredArgsConstructor
@Slf4j
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @GetMapping()
    public ResponseEntity<GetShoppingCardResponse> getShoppingCardWithPendingItems(
            HttpServletRequest httpRequest) {

        log.info("Got get shopping card request");

        String applicationUserId = httpRequest.getHeader(USER_ID);

        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(
                new ApplicationUserId(applicationUserId)
        );
        log.info("Returning {}", shoppingCart);
        log.info("Shopping card id {}  without to String {}", shoppingCart.getShoppingCartId().toString()
        , shoppingCart.getShoppingCartId());

        return ResponseEntity.ok(
                GetShoppingCardResponse.builder()
                        .shoppingCart(
                                shoppingCart
                        )
                        .build()
        );
    }

//    @GetMapping("/history/{applicationUserId}")
//    public ResponseEntity<GetShoppingCartHistoryResponse> getShoppingCardHistory(
////            HttpServletRequest httpRequest) {
//            @PathVariable String applicationUserId) {
//
////        String username = httpRequest.getHeader("username");
//
//        return ResponseEntity.ok(
//                GetShoppingCartHistoryResponse.builder()
//                        .shoppingCart(
//                                shoppingCartService.getShoppingCartHistory(
//                                        new ApplicationUserId(applicationUserId)
//                                )
//                        )
//                        .build()
//        );
//    }

    @PostMapping("/{applicationUserId}")
    public ResponseEntity<AddProductToShoppingCartResponse> addProductToShoppingCard(
            @RequestBody @Valid AddProductToShoppingCartRequest request,
//                                                   HttpServletRequest httpRequest) {
            @PathVariable String applicationUserId) throws InsufficientQuantityException {

//        String username = httpRequest.getHeader("username");
        shoppingCartService.addProductToShoppingCart(
                new ProductId(request.getProductId()),
                new ApplicationUserId(applicationUserId),
                request.getQuantity(),
                request.getPrice(),
                request.getProductName()
        );

        return ResponseEntity.ok(
                AddProductToShoppingCartResponse.builder().build()
        );
    }

    @PutMapping("{shoppingCartId}/item/{shoppingCartItemId}/increment")
    public ResponseEntity<ChangeQuantityResponse> incrementQuantity(
            @PathVariable String shoppingCartId,
            @PathVariable String shoppingCartItemId
    )
            throws MaxQuantityReachedException, RestRequestException {

        shoppingCartService.incrementQuantity(
                new ShoppingCartId(shoppingCartId),
                new ShoppingCartItemId(shoppingCartItemId)
        );

        return ResponseEntity.ok(
                ChangeQuantityResponse.builder().build()
        );
    }

    @PutMapping("{shoppingCartId}/item/{shoppingCartItemId}/decrement")
    public ResponseEntity<ChangeQuantityResponse> decrementQuantity(
            @PathVariable String shoppingCartId,
            @PathVariable String shoppingCartItemId
    )
            throws MinQuantityReachedException {

        shoppingCartService.decrementQuantity(
                new ShoppingCartId(shoppingCartId),
                new ShoppingCartItemId(shoppingCartItemId)
        );

        return ResponseEntity.ok(
                ChangeQuantityResponse.builder().build()
        );
    }

    @DeleteMapping("/delete/{shoppingCartId}/{shoppingCartItemId}")
    public ResponseEntity<DeleteShoppingCartItemResponse> deleteShoppingCartItem(
            @PathVariable String shoppingCartId,
            @PathVariable String shoppingCartItemId) {

        log.info("Deleting shoppingCartItem with id {}", shoppingCartItemId);

        shoppingCartService.deleteShoppingCardItem(
                new ShoppingCartId(shoppingCartId),
                new ShoppingCartItemId(shoppingCartItemId)
        );

        return ResponseEntity.ok(
                DeleteShoppingCartItemResponse.builder().build()
        );
    }

//    @PostMapping("/buy/{shoppingCartId}")
//    public ResponseEntity<BuyResponse> buy(@RequestBody @Valid BuyRequest buyRequest)
//            throws InsufficientQuantityException, FailedToBuyException {
//
//        log.info("BuyRequest: {}", buyRequest);
//
//        shoppingCartService.buy(buyRequest);
//
//        return ResponseEntity.ok(
//                BuyResponse.builder().build()
//        );
//    }

}
