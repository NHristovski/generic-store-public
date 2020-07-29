//package hristovski.nikola.product.domain.service;
//
//import hristovski.nikola.product.domain.exception.FailedToBuyException;
//import hristovski.nikola.product.domain.exception.InvalidQuantityException;
//import hristovski.nikola.product.domain.exception.ProductNotFoundException;
//import hristovski.nikola.product.domain.model.ShoppingCart;
//import hristovski.nikola.product.domain.model.shoppingCart.BuyRequest;
//
//public interface ShoppingCartService {
//
//    ShoppingCart getShoppingCart(Long shoppingCartId);
//
//    ShoppingCart getShoppingCart(String username);
//
//    ShoppingCart getShoppingCartWithPendingItems(String username);
//
//    ShoppingCart addShoppingCart(ShoppingCart shoppingCart);
//
//    void editShoppingCart(ShoppingCart shoppingCart);
//
//    void deleteShoppingCart(Long shoppingCartId);
//
//    void addProductToShoppingCart(Long productId, Integer quantity, String username) throws ProductNotFoundException;
//
//    Double getShoppingCartPrice(Long shoppingCartId);
//
//    ShoppingCart getShoppingCartHistory(String username);
//
//    void deleteItem(Long shoppingCartId, Long itemId);
//
//    void buy(BuyRequest buyRequest) throws InvalidQuantityException, FailedToBuyException, ProductNotFoundException;
//}
