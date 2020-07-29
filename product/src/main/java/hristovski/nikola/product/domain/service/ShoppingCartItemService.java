//package hristovski.nikola.product.domain.service;
//
//import hristovski.nikola.product.domain.exception.MaxQuantityReachedException;
//import hristovski.nikola.product.domain.exception.MinQuantityReachedException;
//import hristovski.nikola.product.domain.model.ShoppingCartItem;
//
//public interface ShoppingCartItemService {
//
//    ShoppingCartItem getShoppingCardItem(Long id);
//    ShoppingCartItem addShoppingCardItem(ShoppingCartItem shoppingCartItem);
//
//    ShoppingCartItem editShoppingCardItem(ShoppingCartItem shoppingCartItem);
//    void deleteShoppingCardItem(long shoppingCartItemId);
//
//    void incrementQuantity(Long id) throws MaxQuantityReachedException;
//    void decrementQuantity(Long id) throws MinQuantityReachedException;
//}
