//package hristovski.nikola.product.controllers;
//
//import hristovski.nikola.product.domain.exception.*;
//import hristovski.nikola.product.domain.model.ShoppingCart;
//import hristovski.nikola.product.domain.model.shoppingCart.AddProductToShoppingCartRequest;
//import hristovski.nikola.product.domain.model.shoppingCart.BuyRequest;
//import hristovski.nikola.product.domain.service.ShoppingCartItemService;
//import hristovski.nikola.product.domain.service.ShoppingCartService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping("/shoppingCart")
//@RequiredArgsConstructor
//@Slf4j
//public class ShoppingCartController {
//
//    private final ShoppingCartService shoppingCartService;
//    private final ShoppingCartItemService shoppingCartItemService;
//
//    @GetMapping()
//    public ResponseEntity<ShoppingCart> getShoppingCardWithPendingItems(HttpServletRequest httpRequest){
//        String username = httpRequest.getHeader("username");
//
//        return ResponseEntity.ok(shoppingCartService.getShoppingCartWithPendingItems(username));
//    }
//
//    @GetMapping("/history")
//    public ResponseEntity<ShoppingCart> getShoppingCardHistory(HttpServletRequest httpRequest) {
//
//        String username = httpRequest.getHeader("username");
//
//        return ResponseEntity.ok(shoppingCartService.getShoppingCartHistory(username));
//    }
//
//    @PostMapping
//    public ResponseEntity addProductToShoppingCard(@RequestBody @Valid AddProductToShoppingCartRequest request,
//                                                   HttpServletRequest httpRequest)
//            throws ProductNotFoundException {
//
//        String username = httpRequest.getHeader("username");
//        shoppingCartService.addProductToShoppingCart(request.getProductId(),
//                request.getQuantity(),
//                username);
//
//        return ResponseEntity.ok(null);
//    }
//
//    @PutMapping("/item/increment/{id}")
//    public ResponseEntity incrementQuantity(@PathVariable Long id) throws MaxQuantityReachedException {
//        shoppingCartItemService.incrementQuantity(id);
//
//        return ResponseEntity.ok(null);
//    }
//
//    @PutMapping("/item/decrement/{id}")
//    public ResponseEntity decrementQuantity(@PathVariable Long id) throws MinQuantityReachedException {
//        shoppingCartItemService.decrementQuantity(id);
//
//        return ResponseEntity.ok(null);
//    }
//
//    @DeleteMapping("/delete/{shoppingCartId}/{itemId}")
//    public ResponseEntity decrementQuantity(@PathVariable Long shoppingCartId,
//                                            @PathVariable Long itemId) {
//        log.info("Deleting item id {}",itemId);
//        shoppingCartService.deleteItem(shoppingCartId, itemId);
//
//        return ResponseEntity.ok(null);
//    }
//
//    @PostMapping("/buy")
//    public ResponseEntity buy(@RequestBody @Valid BuyRequest buyRequest) throws InvalidQuantityException, FailedToBuyException, ProductNotFoundException {
//        log.info("BuyRequest: {}", buyRequest);
//        shoppingCartService.buy(buyRequest);
//
//        return ResponseEntity.ok(null);
//    }
//
//}
